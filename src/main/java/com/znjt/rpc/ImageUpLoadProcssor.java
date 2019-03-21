package com.znjt.rpc;

import com.google.protobuf.ByteString;
import com.znjt.boot.Boot;
import com.znjt.dao.beans.GPSTransferIniBean;
import com.znjt.exs.ExceptionInfoUtils;
import com.znjt.proto.GPSRecord;
import com.znjt.service.GPSTransferService;
import com.znjt.utils.FileIOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by qiuzx on 2019-03-20
 * Company BTT
 * Depart Tech
 * @author qiuzx
 */
public class ImageUpLoadProcssor {
    private static Logger logger = LoggerFactory.getLogger(ImageUpLoadProcssor.class);

    private ImageUpLoadProcssor() {
    }

    /**
     * 处理文件上传和记录更新
     * @param gpsRecords
     * @param gpsTransferService
     * @return
     */
    public static List<GPSRecord> processGPSRecord(List<GPSRecord> gpsRecords, GPSTransferService gpsTransferService) {
        List<ImageProcessorResult> imageProcessorResults = new ArrayList<>();
        List<GPSRecord> res_gps_records = new ArrayList<>();
        List<GPSTransferIniBean> gpsTransferIniBeans = new ArrayList<>();
        //将数据写入磁盘
        Optional.ofNullable(gpsRecords).ifPresent(records -> {
            records.forEach(item -> {
                imageProcessorResults.add(doneImage2Disk(item));
            });
        });

        imageProcessorResults.forEach(item -> {
            //如果图像持久化未成功或者不存在图像
            if (item.isImg_err() || !item.isPersistent()) {
                GPSRecord record = GPSRecord.newBuilder().setClientRecordId(item.getGpsRecord().getClientRecordId())
                        .setServOpsRes(true).setFileErr(true).build();
                res_gps_records.add(record);
            } else {
                GPSTransferIniBean gpsTransferIniBean = new GPSTransferIniBean();
                gpsTransferIniBean.setDataid(item.getGpsRecord().getDataId());
                gpsTransferIniBean.setOriginalUrl(item.getRelPath());
                gpsTransferIniBean.setBaseDir(item.getAbsPath());
                gpsTransferIniBean.setFile_full_path(item.getFilePath());
                gpsTransferIniBean.setClientRecordId(item.getGpsRecord().getClientRecordId());
                gpsTransferIniBeans.add(gpsTransferIniBean);
            }
        });

        try {
            doneImage2DB(gpsTransferService, gpsTransferIniBeans);
            //数据库更新成功，添加新的响应结果
            gpsTransferIniBeans.forEach(item -> {
                GPSRecord record = GPSRecord.newBuilder().setClientRecordId(item.getClientRecordId())
                        .setServOpsRes(true).setFileErr(false).build();
                res_gps_records.add(record);
            });
        } catch (Exception ex) {
            logger.debug(ExceptionInfoUtils.getExceptionCauseInfo(ex));
            //数据库更新成功，添加新的响应结果
            gpsTransferIniBeans.forEach(item -> {
                GPSRecord record = GPSRecord.newBuilder().setClientRecordId(item.getClientRecordId())
                        .setServOpsRes(false).setFileErr(false).build();
                res_gps_records.add(record);
                //删除磁盘上的图像数据
                FileIOUtils.deleteFile(item.getFile_full_path());
            });
        }

        //将结果返回给客户端
        return res_gps_records;
    }

    private static void doneImage2DB(GPSTransferService gpsTransferService, List<GPSTransferIniBean> gpsTransferIniBeans) {
        //批量更新数据
        gpsTransferService.updateBatchGPSImgPath2DBRecord(Boot.UPSTREAM_DBNAME, gpsTransferIniBeans);
    }

    /**
     * 处理图像写入磁盘,并且将操作结果封装到ImageProcessorResult中
     *
     * @param gpsRecord
     * @return
     */
    private static ImageProcessorResult doneImage2Disk(GPSRecord gpsRecord) {
        ByteString byteString = gpsRecord.getImgData();
        boolean img_err = false;
        boolean persistent = false;
        String path = null;
        String sub_path = null;

        if (byteString.isEmpty()) {
            //没有图像数据不处理，直接向客户端方式数据出结果和图像数据状态
            img_err = true;
        } else {
            img_err = false;
            byte[] imgs = byteString.toByteArray();
            sub_path = FileIOUtils.createRelativePath4Image(gpsRecord.getDataId());
            path = TransferProtoImpl4Server.BASE_DIR + sub_path;
            try {
                //保存失败会抛出异常
                FileIOUtils.saveBinaryImg2Disk(path, imgs);
                persistent = true;
            } catch (Exception ex) {
                logger.debug(ExceptionInfoUtils.getExceptionCauseInfo(ex));
                //如果操作失败就删除文件夹中的图像
                FileIOUtils.deleteFile(path);
            }
        }
        ImageProcessorResult result = new ImageProcessorResult(gpsRecord, false, img_err, persistent, path);
        result.setAbsPath(TransferProtoImpl4Server.BASE_DIR);
        result.setRelPath(sub_path);
        return result;
    }
}
