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

import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

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
    private StreamObserver<SyncDataResponse> responseStreamObserver;
    private TransferServiceGrpc.TransferServiceStub stub;
    private ManagedChannel managedChannel;
    private TransferRespJobUtils transferRespJobUtils;
    private Object monitor = new Object();


    TransporterClientProxy(GPSTransferService localTransferService,String addr, int port, int max_batch_size) {
        this.addr = addr;
        this.port = port;
        this.max_batch_size = max_batch_size;
        transferRespJobUtils = new TransferRespJobUtils(localTransferService,monitor, max_batch_size);
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
     * 发送上传数据的操作
     *
     * @param datas
     */
    public void transferData2Server(List<GPSTransferIniBean> datas) {
        if (datas != null && datas.size() > 0) {
            StreamObserver<SyncDataRequest> requestObserver = getAsyncStub().transporterByStream(createResponseStream());
            for (GPSTransferIniBean item : datas) {
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

                if(Objects.isNull(bytes)){
                    gpsRecord = GPSRecord.newBuilder().setClientRecordId(item.getGpsid())
                            .setDataId(item.getDataid())
                            .build();
                }else{
                    gpsRecord = GPSRecord.newBuilder().setClientRecordId(item.getGpsid())
                            .setDataId(item.getDataid())
                            .setImgData(ByteStringUtils.changeBytes2ByteString(bytes))
                            .build();
                }

                requestObserver.onNext(SyncDataRequest.newBuilder().setDataType(DataType.T_GPS).setGpsRecord(gpsRecord).build());

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
    private StreamObserver<SyncDataResponse> createResponseStream() {
        if (responseStreamObserver == null) {
            synchronized (this) {
                if (responseStreamObserver == null) {
                    responseStreamObserver = new StreamObserver<SyncDataResponse>() {
                        @Override
                        public void onNext(SyncDataResponse syncDataResponse) {
                            transferRespJobUtils.addNewJob(syncDataResponse);
                        }

                        @Override
                        public void onError(Throwable throwable) {

                        }

                        @Override
                        public void onCompleted() {
                            logger.debug("服务端处理本次请求的所有响应完毕...关闭流..");
                        }
                    };
                }
            }
        }
        return responseStreamObserver;
    }
}
