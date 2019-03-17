package com.znjt.rpc;

import com.google.protobuf.ByteString;
import com.znjt.exs.ExceptionInfoUtils;
import com.znjt.proto.*;
import com.znjt.utils.FileIOUtils;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;


/**
 * Created by qiuzx on 2019-03-14
 * Company BTT
 * Depart Tech
 *
 * @author qiuzx
 */
public class TransferProtoImpl4Server extends TransferServiceGrpc.TransferServiceImplBase {
    private static Logger logger = LoggerFactory.getLogger(TransferProtoImpl4Server.class);
    //private static ExecutorService executorService = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    @Override
    public StreamObserver<SyncDataRequest> transporterByStream(StreamObserver<SyncDataResponse> responseObserver) {
        StreamObserver<SyncDataRequest> streamObserver = new StreamObserver<SyncDataRequest>() {
            @Override
            public void onNext(SyncDataRequest syncDataRequest) {
                System.err.println("server say:  receive request from client ......");
                doneClientRequest(syncDataRequest,responseObserver);
               // executorService.execute(new Task(syncDataRequest, responseObserver));
            }

            @Override
            public void onError(Throwable throwable) {
                logger.error(ExceptionInfoUtils.getExceptionCauseInfo(throwable));
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
        return streamObserver;
    }

    private void doneClientRequest(SyncDataRequest syncDataRequest, StreamObserver<SyncDataResponse> responseObserver) {
        //GPS表
        if (syncDataRequest.getDataType() == DataType.T_GPS) {
            GPSRecord gpsRecord = syncDataRequest.getGpsRecord();

            //TODO处理客户端图像
            String dataId = gpsRecord.getDataId();
            ByteString byteString = gpsRecord.getImgData();
            boolean ops_res = false;
            boolean img_err = false;
            System.err.println("server img bytes status has data? === " + !byteString.isEmpty());
            GPSRecord record = null;
            if (byteString.isEmpty()) {
                //没有图像数据不处理，直接向客户端方式数据出结果和图像数据状态
                ops_res = true;
                img_err = true;
            } else {
                img_err = false;
                //TODO 保存图像数据
                byte[] imgs = byteString.toByteArray();
                System.err.println("imgs size = " + imgs.length+" bytes");
                String temp = "/Users/qiuzx/IdeaProjects/qiuzx/deliverc/imgs/";
                FileIOUtils.saveBinaryImg2Disk(temp+gpsRecord.getClientRecordId()+".jpg",imgs);
                ops_res = true;
            }
            record = GPSRecord.newBuilder().setClientRecordId(gpsRecord.getClientRecordId())
                    .setServOpsRes(ops_res).setFileErr(img_err).build();
            SyncDataResponse response = SyncDataResponse.newBuilder()
                    .setDataType(DataType.T_GPS)
                    .setGpsRecord(record)
                    .build();
            responseObserver.onNext(response);
        } else if (syncDataRequest.getDataType() == DataType.T_INI) {

        }

    }

    /**
     * 处理客户端请求任务
     */
    class Task implements Runnable {
        SyncDataRequest syncDataRequest;
        StreamObserver<SyncDataResponse> responseObserver;

        Task() {
            this.syncDataRequest = syncDataRequest;
            this.responseObserver = responseObserver;
        }

        @Override
        public void run() {
        }
    }

//    public void close() {
//        if (executorService != null) {
//            if (!executorService.isShutdown() || !executorService.isTerminated()) {
//                try {
//                    if (logger.isInfoEnabled()) {
//                        logger.info("处理Server端任务的线程开始关闭..最长等待60S");
//                    }
//                    executorService.shutdown();
//                    executorService.awaitTermination(60, TimeUnit.SECONDS);
//                    if (executorService.isTerminated()) {
//                        if (logger.isInfoEnabled()) {
//                            logger.info("处理Server端任务的线程成功关闭..");
//                        }
//                    } else {
//                        if (logger.isInfoEnabled()) {
//                            logger.info("处理Server端任务的线程关闭..超时，强行退出...");
//                        }
//                        executorService.shutdownNow();
//                    }
//
//                } catch (InterruptedException e) {
//                    if (logger.isInfoEnabled()) {
//                        logger.info("处理Server端任务的线程关闭..被Interrupted");
//                    }
//                }
//            }
//        }
//    }
}
