package com.znjt.boot;

import com.mysql.jdbc.log.LogUtils;
import com.znjt.dao.beans.ACCTransferIniBean;
import com.znjt.dao.beans.GPSTransferIniBean;
import com.znjt.net.NetQuality;
import com.znjt.net.NetStatusUtils;
import com.znjt.net.NetUtils;
import com.znjt.rpc.TransportClient;
import com.znjt.rpc.UpLoadReson;
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
    private static final int INC_STEP = 5;
    private static final long TIME_WATER_LINE = 1500;

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

        //开始批上传的速度控制在2条，如果带宽准许，会逐步提升上传记录记录数。
        int dync_batch_size = 2;
        List<GPSTransferIniBean> recordDatas = gpsTransferService.findUnUpLoadGPSImgDatas(Boot.DOWNSTREAM_DBNAME, Boot.IMAGE_BATCH_SIZE);
        invoke_time++;
        UpLoadReson upLoadReson;
        long uplaod_speed = -1;
        long consumerMils = -1;
        while (recordDatas != null && recordDatas.size() > 0) {
            limiting();
            logger.info("开始上传gps图像数据...[" + recordDatas.size() + "]条");
            //上传图像，返回上传的速率
            upLoadReson = client.uploadBigDataByRPC(recordDatas,by_sync_single);
            //对批量上传的图像数据进行速度判断，如果速度达不到就自动降低批量上传的大小
            if(!by_sync_single&&upLoadReson!=null){
//                uplaod_speed = upLoadReson.getSpeed();
//                if(uplaod_speed>0){
//                    //转换为100KB的单位
//                    uplaod_speed = uplaod_speed/(1024*100);
//                    //没有达到100KB每秒
//                    if(uplaod_speed==0){
//                        dync_batch_size = dync_batch_size/2;
//                    }else{
//                        dync_batch_size = dync_batch_size*2;
//                    }
//                    //现在批量最小值
//                    if(dync_batch_size<2){
//                        dync_batch_size = 2;
//                    }
//                    //限制批量最大值
//                    if(dync_batch_size>Boot.IMAGE_BATCH_SIZE){
//                        dync_batch_size = Boot.IMAGE_BATCH_SIZE;
//                    }
//                }
                uplaod_speed = upLoadReson.getSpeed();

                if(uplaod_speed>0){
                    //转换为100KB的单位
                    uplaod_speed = uplaod_speed/(1024*100);
                    //获取消耗时间
                    consumerMils = upLoadReson.getConsumeMins();
                    StringBuilder sb = new StringBuilder();
                    //没有达到100KB每秒
                    if(uplaod_speed==0||consumerMils>TIME_WATER_LINE){
                        //快速降低批量大小
                        dync_batch_size = dync_batch_size/2;
                        if(uplaod_speed<=0){
                            sb.append("上传速度["+uplaod_speed+"]KB,小余100KB. ");
                        }
                        if(consumerMils>TIME_WATER_LINE){
                            sb.append("上传耗时["+consumerMils+"]ms,大于["+TIME_WATER_LINE+"]ms.");
                        }
                        LoggerUtils.info(logger,sb.toString() + " 尝试减少单次批量记录为原来的一半");
                    }else{
                        //尝试递增
                        dync_batch_size = dync_batch_size+=INC_STEP;
                        LoggerUtils.info(logger,"网络带宽良好,耗时小余"+TIME_WATER_LINE+"ms，尝试增加单次批量记录["+INC_STEP+"]");
                    }
                    //现在批量最小值
                    if(dync_batch_size<2){
                        dync_batch_size = 2;
                    }
                    //限制批量最大值
                    if(dync_batch_size>Boot.IMAGE_BATCH_SIZE){
                        dync_batch_size = Boot.IMAGE_BATCH_SIZE;
                    }
                }

            }

            recordDatas = gpsTransferService.findUnUpLoadGPSImgDatas(Boot.DOWNSTREAM_DBNAME, dync_batch_size);
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
