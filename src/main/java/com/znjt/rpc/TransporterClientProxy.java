package com.znjt.rpc;

import com.znjt.dao.beans.GPSTransferIniBean;
import com.znjt.exs.ExceptionInfoUtils;
import com.znjt.proto.*;
import com.znjt.service.GPSTransferService;
import com.znjt.utils.FileIOUtils;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by qiuzx on 2019-03-14
 * Company BTT
 * Depart Tech
 *
 * @author qiuzx
 */
public class TransporterClientProxy {
    private final Logger logger = LoggerFactory.getLogger(TransporterClientProxy.class);
    private String addr;
    private int port;
    private int max_batch_size;
    private JobCounterStreamObserver responseStreamObserver;
    private TransferServiceGrpc.TransferServiceStub stub;
    private TransferServiceGrpc.TransferServiceBlockingStub blockingStub;
    private ManagedChannel managedChannel;
    private TransferRespJobUtils transferRespJobUtils;
    private Object monitor = new Object();


    TransporterClientProxy(GPSTransferService localTransferService, String addr, int port, int max_batch_size) {
        this.addr = addr;
        this.port = port;
        this.max_batch_size = max_batch_size;
        transferRespJobUtils = new TransferRespJobUtils(localTransferService, monitor, max_batch_size);
    }

    /**
     * 对象重用
     *
     * @return
     */
    private ManagedChannel getManagedChannel() {
        if (managedChannel == null) {
            synchronized (this) {
                if (managedChannel == null) {
                    managedChannel = ManagedChannelBuilder.forAddress(addr, port).usePlaintext().build();
                }
            }
        }
        return managedChannel;
    }

    /**
     * 对象重用
     *
     * @return
     */
    private TransferServiceGrpc.TransferServiceStub getAsyncStub() {
        if (stub == null) {
            synchronized (this) {
                if (stub == null) {
                    ManagedChannel channel = getManagedChannel();
                    if (channel != null) {
                        if (!(channel.isTerminated() || channel.isShutdown())) {
                            stub = TransferServiceGrpc.newStub(channel);
                        }
                    }
                }
            }
        }
        return stub;
    }

    /**
     * 对象重用
     *
     * @return
     */
    private TransferServiceGrpc.TransferServiceBlockingStub getSyncStub() {
        if (blockingStub == null) {
            synchronized (this) {
                if (blockingStub == null) {
                    ManagedChannel channel = getManagedChannel();
                    if (channel != null) {
                        if (!(channel.isTerminated() || channel.isShutdown())) {
                            blockingStub = TransferServiceGrpc.newBlockingStub(channel);
                        }
                    }
                }
            }
        }
        return blockingStub;
    }
    /**
     * 同步方式发送上传数据的操作
     *
     * @param datas
     */
    public void transferData2ServerBySync(List<GPSTransferIniBean> datas) {
        if (datas != null && datas.size() > 0) {
            for (GPSTransferIniBean item : datas) {
                SyncDataRequest request = createRequestObj(item);
                try {
                    SyncDataResponse response = getSyncStub().transporterBySync(request);
                    transferRespJobUtils.addNewJob(response);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    private SyncDataRequest createRequestObj(GPSTransferIniBean item){
        byte[] bytes = null;
        try {
            bytes = FileIOUtils.getImgBytesDataFromPath(item.getOriginalUrl());
        } catch (Exception ex) {
            if (logger.isErrorEnabled()) {
                logger.error("读取_gpsid=" + item.getGpsid() + "_dataid=" + item.getDataid() + "_originalUrl=" + item.getOriginalUrl() + " 失败. 原因：" + ExceptionInfoUtils.getExceptionCauseInfo(ex));
            }
        }
        if (bytes == null) {
            logger.warn("读取_gpsid=" + item.getGpsid() + "_dataid=" + item.getDataid() + "_originalUrl=" + item.getOriginalUrl() + " 失败");
        }

        GPSRecord gpsRecord = null;

        if (Objects.isNull(bytes)) {
            gpsRecord = GPSRecord.newBuilder().setClientRecordId(item.getGpsid())
                    .setDataId(item.getDataid())
                    .build();
        } else {
            gpsRecord = GPSRecord.newBuilder().setClientRecordId(item.getGpsid())
                    .setDataId(item.getDataid())
                    .setImgData(ByteStringUtils.changeBytes2ByteString(bytes))
                    .build();
        }
        return SyncDataRequest.newBuilder().setDataType(DataType.T_GPS).setGpsRecord(gpsRecord).build();
    }
    /**
     * 异步发送上传数据的操作
     *
     * @param datas
     */
    public void transferData2Server(List<GPSTransferIniBean> datas) {
        if (datas != null && datas.size() > 0) {
            JobCounterStreamObserver responseStreamObserver = createResponseStream();

            StreamObserver<SyncDataRequest> requestObserver = getAsyncStub().transporterByStream(responseStreamObserver);

            for (GPSTransferIniBean item : datas) {
//                byte[] bytes = null;
//                try {
//                    bytes = FileIOUtils.getImgBytesDataFromPath(item.getOriginalUrl());
//                } catch (Exception ex) {
//                    if (logger.isErrorEnabled()) {
//                        logger.error("读取_gpsid=" + item.getGpsid() + "_dataid=" + item.getDataid() + "_originalUrl=" + item.getOriginalUrl() + " 失败. 原因：" + ExceptionInfoUtils.getExceptionCauseInfo(ex));
//                    }
//                }
//                if (bytes == null) {
//                    logger.warn("读取_gpsid=" + item.getGpsid() + "_dataid=" + item.getDataid() + "_originalUrl=" + item.getOriginalUrl() + " 失败");
//                }
//
//                GPSRecord gpsRecord = null;
//
//                if (Objects.isNull(bytes)) {
//                    gpsRecord = GPSRecord.newBuilder().setClientRecordId(item.getGpsid())
//                            .setDataId(item.getDataid())
//                            .build();
//                } else {
//                    gpsRecord = GPSRecord.newBuilder().setClientRecordId(item.getGpsid())
//                            .setDataId(item.getDataid())
//                            .setImgData(ByteStringUtils.changeBytes2ByteString(bytes))
//                            .build();
//                }
                requestObserver.onNext(createRequestObj(item));
                long remain = responseStreamObserver.incNewJobCounter();

                if (logger.isDebugEnabled()) {
                    logger.debug("Inc Job .... 还剩下[" + remain + "]个图像上传请求没有收到服务端的响应");
                }
            }

            //尝试等待服务器端的响应全部返回,最大等待5S
            int wait_times = 0;
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
                wait_times++;
                if (!responseStreamObserver.has_un_response_jobs()||wait_times>4){
                    break;
                }
                System.err.println("尝试等待服务端响应所有请求.......第["+wait_times+"]S,还剩下["+responseStreamObserver.get_remain_un_response_jbos()+"]未响应");
            }
            //表示发送完毕
            requestObserver.onCompleted();
        }
    }

    /**
     * 关闭资源
     */
    private void shutdown() {
        if (managedChannel != null) {
            try {
                managedChannel.shutdown().awaitTermination(5, TimeUnit.MINUTES);
                logger.info("Client managedChannel closed!");
            } catch (InterruptedException e) {
            }
        }
        managedChannel = null;
        stub = null;
    }

    public void release() {
        if (transferRespJobUtils != null) {
            transferRespJobUtils.close();
        }
    }

    /**
     * 创建处理响应的回调对象
     *
     * @return
     */
    private JobCounterStreamObserver createResponseStream() {
        if (responseStreamObserver == null) {
            synchronized (this) {
                if (responseStreamObserver == null) {
                    responseStreamObserver = new JobCounterStreamObserver();
                }
            }
        }
        return responseStreamObserver;
    }

    private class JobCounterStreamObserver implements StreamObserver<SyncDataResponse> {
        //记录未得到响应结果的任务总数
        private AtomicLong un_response_jobs = new AtomicLong();

        @Override
        public void onNext(SyncDataResponse syncDataResponse) {
            transferRespJobUtils.addNewJob(syncDataResponse);
            long remain = decJobCounter();
            if (logger.isDebugEnabled()) {
                logger.debug("Dec Job .... 还剩下[" + remain + "]个图像上传请求没有收到服务端的响应");
            }
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onCompleted() {
            logger.debug("服务端处理本次请求的所有响应完毕...关闭流..");
        }

        /**
         * 是否存在未响应的job
         */
        public boolean has_un_response_jobs() {
            if (un_response_jobs.get() > 0) {
                return true;
            } else {
                return false;
            }
        }

        public long get_remain_un_response_jbos(){
            return un_response_jobs.get();
        }

        public long incNewJobCounter() {
            return un_response_jobs.incrementAndGet();
        }

        public long decJobCounter() {
            return un_response_jobs.decrementAndGet();
        }
    }
}
