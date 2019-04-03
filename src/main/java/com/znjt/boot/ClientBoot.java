package com.znjt.boot;

import com.znjt.dao.beans.ACCTransferIniBean;
import com.znjt.dao.beans.GPSTransferIniBean;
import com.znjt.net.NetQuality;
import com.znjt.net.NetStatusUtils;
import com.znjt.net.NetUtils;
import com.znjt.rpc.TransportClient;
import com.znjt.service.ACCTransferService;
import com.znjt.service.GPSTransferService;
import com.znjt.utils.LoggerUtils;
import io.grpc.netty.shaded.io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by qiuzx on 2019-03-14
 * Company BTT
 * Depart Tech
 *
 * @author qiuzx
 */
public class ClientBoot {
    private Logger logger = LoggerFactory.getLogger(ClientBoot.class);

    private static ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors()*4, new DefaultThreadFactory());
    private String server_ip = null;
    private String ip_pattern = null;
    private int server_port = 9898;
    //网络是否准许连接（是否畅通）
    private volatile boolean net_allowed_connect = false;
    private volatile boolean exit_sys_stauts = false;
    private ACCTransferService accTransferService;
    private GPSTransferService gpsTransferService;
    private TransportClient client;
    private static  boolean RATE_LIMITING = true;

    /**
     * 启动客户端任务
     *
     * @param server_ip
     * @param server_port
     */
    public void start_client_jobs(String server_ip, int server_port,String ip_pattern) {
        this.server_ip = server_ip;
        this.server_port = server_port;
        this.ip_pattern = ip_pattern;
        if (server_ip == null || server_ip.equals("")) {
            throw new RuntimeException("IP地址[" + server_ip + "]不合法，无法启动客户端");
        }
        init();
    }


    private void init() {
        RATE_LIMITING = Boot.expire();
        accTransferService = new ACCTransferService();
        gpsTransferService = new GPSTransferService();
        client = new TransportClient(gpsTransferService, server_ip, server_port, Boot.IMAGE_BATCH_SIZE);
        start_monitor_jobs();
    }

    /**
     * 启动任务
     */
    private void start_monitor_jobs() {
        start_net_jobs();
        start_gps_record_jobs();
        start_gps_img_jobs();
        start_acc_jobs();
    }

    public void terminal_jobs() {
        if(client!=null){
            client.release();
        }
        exit_sys_stauts = true;
    }

    private void start_net_jobs() {
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                NetQuality netQuality = NetStatusUtils.getNetworkQuality(server_ip, 5);
                if (netQuality == NetQuality.BAD || netQuality == NetQuality.BREOKEN) {
                    net_allowed_connect = false;
                } else {
                    net_allowed_connect = true;
                }
                logger.info("网络质量：" + netQuality.getQuality());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }, 5, 5, TimeUnit.SECONDS);

    }

    private void start_acc_jobs() {
        try {
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                if (net_allowed_connect) {
                    try {
                        start_monitor_acc();
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }

                }
            }, 2, 10, TimeUnit.SECONDS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void start_gps_record_jobs() {
        try {
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                if (net_allowed_connect) {
                    try {
                        start_monitor_gps_records();
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }, 3, 10, TimeUnit.SECONDS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void start_gps_img_jobs() {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            if (net_allowed_connect) {
                try {
                    if(!"*".equals(ip_pattern)){
                        if(NetUtils.is_inner_network_ip(ip_pattern)){
                            start_monitor_gps_img();
                        }else {
                            LoggerUtils.info(logger,"当前网络环境为不准许上传图像数据.");
                        }
                    }else{
                        LoggerUtils.info(logger,"当前环境禁止执行图像上传操作");
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        }, 3, 10, TimeUnit.SECONDS);
    }

    /**
     * 检查gps中未上传的img信息
     */
    private void start_monitor_gps_img() {
        //当前执行到第几层
        long invoke_time = 0;
        boolean by_sync_single = true;
        logger.info("检查是否存在要上传的gps图像数据...");
        List<GPSTransferIniBean> recordDatas = gpsTransferService.findUnUpLoadGPSImgDatas(Boot.DOWNSTREAM_DBNAME, Boot.IMAGE_BATCH_SIZE);
        invoke_time++;
        while (recordDatas != null && recordDatas.size() > 0) {
            limiting();
            logger.info("开始上传gps图像数据...[" + recordDatas.size() + "]条");
            client.uploadBigDataByRPC(recordDatas,by_sync_single);
            recordDatas = gpsTransferService.findUnUpLoadGPSImgDatas(Boot.DOWNSTREAM_DBNAME, Boot.IMAGE_BATCH_SIZE);
            invoke_time++;
            /*
              前2次采用同步单条方式传输，防止前期没有来得及更新的数据造成了图像重复存储，造成服务器端出现僵尸数据
             */
            if(invoke_time>Boot.PRE_UPLOAD_IMAGE_BY_SYNC_SINGLE_TIMES){
                by_sync_single = false;
            }
            if (!net_allowed_connect||exit_sys_stauts) {
                break;
            }
        }
        logger.info("没有gps图像数据需要上传...");
    }

    /**
     * 检查gps未上传的记录
     */
    private void start_monitor_gps_records() {
        LoggerUtils.info(logger,"检查是否存在要上传的gps记录数据...");
        List<GPSTransferIniBean> recordDatas = gpsTransferService.findUnUpLoadGPSRecordDatas(Boot.DOWNSTREAM_DBNAME, Boot.RECORD_BATCH_SIZE);
        while (recordDatas != null && recordDatas.size() > 0) {
            limiting();
            LoggerUtils.info(logger,"开始批量上传GPS记录[" + recordDatas.size() + "]条");
            gpsTransferService.upLoadGPSRecordDatas2UpStream(Boot.UPSTREAM_DBNAME, recordDatas);
            gpsTransferService.updateCurrentUpLoadedSuccessGPSRescords(Boot.DOWNSTREAM_DBNAME, recordDatas);
            recordDatas = gpsTransferService.findUnUpLoadGPSRecordDatas(Boot.DOWNSTREAM_DBNAME, Boot.RECORD_BATCH_SIZE);
            if (!net_allowed_connect||exit_sys_stauts) {
                break;
            }
        }
        LoggerUtils.info(logger,"没有gps记录数据需要上传...");
    }

    /**
     * 检查未上传的acc记录
     */
    private void start_monitor_acc() {
        LoggerUtils.info(logger,"检查是否存在要上传的ACC记录数据...");
        List<ACCTransferIniBean> recordDatas = accTransferService.findUnUpLoadACCRecordDatas(Boot.DOWNSTREAM_DBNAME, Boot.RECORD_BATCH_SIZE);
        while (recordDatas != null && recordDatas.size() > 0) {
            limiting();
            LoggerUtils.info(logger,"开始批量上传ACC记录[" + recordDatas.size() + "]条");
            accTransferService.upLoadACCRecordDatas2UpStream(Boot.UPSTREAM_DBNAME, recordDatas);
            accTransferService.updateCurrentUpLoadedSuccessACCRescords(Boot.DOWNSTREAM_DBNAME, recordDatas);
            recordDatas = accTransferService.findUnUpLoadACCRecordDatas(Boot.DOWNSTREAM_DBNAME, Boot.RECORD_BATCH_SIZE);
            //如果网络不准许就中断任务
            if (!net_allowed_connect||exit_sys_stauts) {
                break;
            }
        }
        LoggerUtils.info(logger,"没有ACC记录数据需要上传...");
    }

    private void limiting(){
        if(RATE_LIMITING){
            if(logger.isWarnEnabled()){
                logger.warn("限速中.....");
            }
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(5000,10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The default thread factory
     */
    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "Client-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            t.setDaemon(true);
            return t;
        }
    }
}
