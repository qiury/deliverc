package com.znjt.rpc;

import com.google.protobuf.ByteString;
import com.znjt.utils.CommonFileUitls;
import com.znjt.boot.Boot;
import com.znjt.dao.beans.GPSTransferIniBean;
import com.znjt.exs.ExceptionInfoUtils;
import com.znjt.proto.*;
import com.znjt.service.GPSTransferService;
import com.znjt.utils.FileIOUtils;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * Created by qiuzx on 2019-03-14
 * Company BTT
 * Depart Tech
 *
 * @author qiuzx
 */
public class TransferProtoImpl4Server extends TransferServiceGrpc.TransferServiceImplBase {
    private static Logger logger = LoggerFactory.getLogger(TransferProtoImpl4Server.class);
    public static final String BASE_DIR = CommonFileUitls.getProjectPath();
    private GPSTransferService gpsTransferService = new GPSTransferService();
    static {
        FileIOUtils.init_fs_dirs(BASE_DIR,"/fs/");
    }

    /**
     * 同步批处理
     * @param request
     * @param responseObserver
     */
    @Override
    public void transporterMulBySync(SyncMulImgRequest request, StreamObserver<SyncMulImgResponse> responseObserver) {
        if(request.getDataType()==DataType.T_GPS){
            List<GPSRecord> records = ImageUpLoadProcssor.processGPSRecord(request.getGpsRecordList(),gpsTransferService);
            SyncMulImgResponse syncMulImgResponse = SyncMulImgResponse.newBuilder().setDataType(DataType.T_GPS).addAllGpsRecord(records).build();
            responseObserver.onNext(syncMulImgResponse);
        }
        responseObserver.onCompleted();
    }

    /**
     * 同步处理请求的方式
     * @param request
     * @param responseObserver
     */
    @Override
    public void transporterBySync(SyncDataRequest request, StreamObserver<SyncDataResponse> responseObserver) {
        doneClientRequest(request,responseObserver);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<SyncDataRequest> transporterByStream(StreamObserver<SyncDataResponse> responseObserver) {
        StreamObserver<SyncDataRequest> streamObserver = new StreamObserver<SyncDataRequest>() {
            @Override
            public void onNext(SyncDataRequest syncDataRequest) {
                logger.debug("server say:  receive img upload request from client ......");
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
            //处理客户端图像
            String dataId = gpsRecord.getDataId();
            ByteString byteString = gpsRecord.getImgData();
            boolean ops_res = false;
            boolean img_err = false;
            GPSRecord record = null;
            if (byteString.isEmpty()) {
                //没有图像数据不处理，直接向客户端方式数据出结果和图像数据状态
                ops_res = true;
                img_err = true;
            } else {
                img_err = false;
                byte[] imgs = byteString.toByteArray();
                String sub_path = FileIOUtils.createRelativePath4Image(gpsRecord.getDataId());
                String path = BASE_DIR + sub_path;
                try {
                    //保存失败会抛出异常
                    FileIOUtils.saveBinaryImg2Disk(path,imgs);
                    //更新数据中的路径信息
                    GPSTransferIniBean gpsTransferIniBean = new GPSTransferIniBean();
                    gpsTransferIniBean.setDataid(gpsRecord.getDataId());
                    gpsTransferIniBean.setOriginalUrl(sub_path);
                    gpsTransferIniBean.setBaseDir(BASE_DIR);
                    int res = gpsTransferService.updateGPSImgPath2DBRecord(Boot.UPSTREAM_DBNAME,gpsTransferIniBean);
                    ops_res = true;
                    //说明没有记录被更新(数据重复上传)
                    if(res==0){
                        logger.warn("Warn：路径在"+path+"图像，名称为["+gpsRecord.getDataId()+".jgp]已经存在，进行去重操作...");
                        FileIOUtils.deleteFile(path);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    //如果操作失败就删除文件夹中的图像
                    FileIOUtils.deleteFile(path);
                    ops_res = false;
                }
            }
            record = GPSRecord.newBuilder().setClientRecordId(gpsRecord.getClientRecordId())
                    .setServOpsRes(ops_res).setFileErr(img_err).build();
            SyncDataResponse response = SyncDataResponse.newBuilder()
                    .setDataType(DataType.T_GPS)
                    .setGpsRecord(record)
                    .build();
            responseObserver.onNext(response);
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
