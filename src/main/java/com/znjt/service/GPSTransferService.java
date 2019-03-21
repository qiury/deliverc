package com.znjt.service;

import com.znjt.dao.beans.GPSTransferIniBean;
import com.znjt.dao.impl.GPSTransferDao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by qiuzx on 2019-03-15
 * Company BTT
 * Depart Tech
 * @author qiuzx
 */
public class GPSTransferService {
    private GPSTransferDao dao = new GPSTransferDao();
    /**
     * 获取没有上传的数据记录
     * @param pageSize
     * @return
     */
    public List<GPSTransferIniBean> findUnUpLoadGPSRecordDatas(String dbname,int pageSize){
        return dao.findUnUpLoadGPSRecordDatas(dbname,pageSize);
    }

    /**
     * 获取没有上传图像但是已经上传了记录的数据
     * @param dbname
     * @param pageSize
     * @return
     */
    public List<GPSTransferIniBean> findUnUpLoadGPSImgDatas(String dbname,int pageSize) {
        return dao.findUnUpLoadGPSImgDatas(dbname,pageSize);
    }

    /**
     * 更新GPS记录上传成功
     * @param dbname
     * @param gpsTransferIniBeans
     */
    public void updateCurrentUpLoadedSuccessGPSRescords(String dbname,List<GPSTransferIniBean> gpsTransferIniBeans){
        dao.updateCurrentUpLoadedSuccessGPSRescords(dbname,gpsTransferIniBeans);
    }

    /**
     * 更新记录中的图像上传成功
     * @param dbname
     * @param gpsTransferIniBeans
     */
    public void updateCurrentUploadedSuccessGPSImgRecords(String dbname,List<GPSTransferIniBean> gpsTransferIniBeans){
        dao.updateCurrentUploadedSuccessGPSImgRecords(dbname,gpsTransferIniBeans);
    }

    /**
     * 上传记录到上游数据库
     * @param dbname
     * @param gpsTransferIniBeans
     */
    public void upLoadGPSRecordDatas2UpStream(String dbname,List<GPSTransferIniBean> gpsTransferIniBeans){
        //将gpsid和status字段组合成dataid
        Optional.ofNullable(gpsTransferIniBeans).ifPresent(gbs->{
            gbs.forEach(item->{
                if(Objects.isNull(item.getDataid())||!item.getDataid().contains("&")) {
                    item.setDataid(item.getStatus() + "&" + item.getGpsid());
                }
            });
        });
        dao.upLoadGPSRecordDatas2UpStream(dbname,gpsTransferIniBeans);
    }

    public int updateGPSImgPath2DBRecord(String dbName,GPSTransferIniBean gpsTransferIniBean){
        return dao.updateGPSImgPath2DBRecord(dbName,gpsTransferIniBean);
    }

    public void updateBatchGPSImgPath2DBRecord(String dbName,List<GPSTransferIniBean> gpsTransferIniBeans){
        dao.updateBatchGPSImgPath2DBRecord(dbName,gpsTransferIniBeans);
    }


}
