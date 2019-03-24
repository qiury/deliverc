package com.znjt.rpc;

import com.google.protobuf.ByteString;
import com.znjt.boot.Boot;
import com.znjt.dao.beans.GPSTransferIniBean;
import com.znjt.exs.ExceptionInfoUtils;
import com.znjt.proto.GPSRecord;
import com.znjt.service.GPSTransferService;
import com.znjt.utils.FileIOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        //存储对每一个GPSRecord保存到磁盘的结果
        List<GPSTransferIniBean> gpsTransferIniBeans = new ArrayList<>();
        List<GPSRecord> res_gps_records = new ArrayList<>();
        //处理每一个GPSRecord对象
        Optional.ofNullable(gpsRecords).ifPresent(gr->{
            gr.forEach(item->{
                Optional.ofNullable(item).ifPresent(grs->{
                    gpsTransferIniBeans.add(processGPSRecord(grs));
                });
            });
        });

        try {
            doneImage2DB(gpsTransferService, gpsTransferIniBeans);
            //数据库更新成功，添加新的响应结果
            gpsTransferIniBeans.forEach(item -> {
                GPSRecord record = GPSRecord.newBuilder().setClientRecordId(item.getClientRecordId())
                        .setServOpsRes(true)
                        .setFileErr(item.getTotal_losted_size()>0?true:false)
                        .build();
                res_gps_records.add(record);
            });
        } catch (Exception ex) {
            logger.debug(ExceptionInfoUtils.getExceptionCauseInfo(ex));
            //数据库更新成功，添加新的响应结果
            gpsTransferIniBeans.forEach(item -> {
                GPSRecord record = GPSRecord.newBuilder().setClientRecordId(item.getClientRecordId())
                        .setServOpsRes(false).setFileErr(false).build();
                res_gps_records.add(record);
                iterDelFiles(item.getOriginalUrl());
            });
        }

        //将结果返回给客户端
        return res_gps_records;
    }

    /**
     * 递归删除文件，文件路径通过;拼接
     * @param path
     */
    public static void iterDelFiles(String path){
        if(StringUtils.isNotBlank(path)){
            String[] subPaths = path.split(";");
            for (String sp : subPaths) {
                //删除磁盘上的图像数据
                FileIOUtils.deleteFile(TransferProtoImpl4Server.BASE_DIR+sp);
            }
        }
    }

    /**
     * 处理单条GPSRecord
     * @param gpsRecords
     * @return
     */
    public static GPSTransferIniBean processGPSRecord(GPSRecord gpsRecords) {
        String dataId = gpsRecords.getDataId();
        List<ByteString> imgDataList = gpsRecords.getImgDataList();
        SingleImgaeProcessResult sipr = null;
        String join_path = "";
        //客户端自身问题造成的数据丢失+本地原因丢失
        int losted_size = gpsRecords.getLostedSize();
        if(imgDataList!=null){
            for(ByteString bs:imgDataList){
                sipr = doneSingleImage2Disk(dataId,bs.toByteArray());
                if(sipr.isPersistent()){
                    //将相对路径组合在一起
                    join_path = String.join(";",join_path,sipr.getRelPath());
                }else{
                    losted_size++;
                }
            }
        }
        GPSTransferIniBean gpsTransferIniBean = new GPSTransferIniBean();
        gpsTransferIniBean.setDataid(gpsRecords.getDataId());
        gpsTransferIniBean.setOriginalUrl(join_path);
        gpsTransferIniBean.setBaseDir(TransferProtoImpl4Server.BASE_DIR);
        gpsTransferIniBean.setClientRecordId(gpsRecords.getClientRecordId());
        //服务端和客户端共计丢失的总数
        gpsTransferIniBean.setTotal_losted_size(losted_size);
        gpsTransferIniBean.setFile_err(losted_size>0?true:false);
        return gpsTransferIniBean;
    }


    /**
     * 写入单个文件到磁盘
     * @param imgs
     * @return
     */
    private static SingleImgaeProcessResult doneSingleImage2Disk(String dataId, byte[] imgs){
        String sub_path = FileIOUtils.createRelativePath4Image(dataId);
        String path = TransferProtoImpl4Server.BASE_DIR + sub_path;
        boolean persistent = false;
        try {
            //保存失败会抛出异常
            FileIOUtils.saveBinaryImg2Disk(path, imgs);
            persistent = true;
        } catch (Exception ex) {
            logger.debug(ExceptionInfoUtils.getExceptionCauseInfo(ex));
            //如果操作失败就删除文件夹中的图像
            FileIOUtils.deleteFile(path);
        }
        return new SingleImgaeProcessResult(false,persistent,path,sub_path);
    }

    private static void doneImage2DB(GPSTransferService gpsTransferService, List<GPSTransferIniBean> gpsTransferIniBeans) {
        //批量更新数据
        gpsTransferService.updateBatchGPSImgPath2DBRecord(Boot.UPSTREAM_DBNAME, gpsTransferIniBeans);
    }



}
