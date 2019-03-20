package com.znjt.dao.mapper;

import com.znjt.dao.beans.GPSTransferIniBean;
import com.znjt.datasource.enhance.Mapper;

import java.util.List;

/**
 * Created by qiuzx on 2019-03-12
 * Company BTT
 * Depart Tech
 * @author qiuzx
 */
public interface GPSTransferBeanMapper extends Mapper {
    /**
     * 获取没有上传的数据记录
     * @param pageSize
     * @return
     */
    List<GPSTransferIniBean> findUnUpLoadGPSRecordDatas(int pageSize);

    List<GPSTransferIniBean> findUnUpLoadGPSImgDatas(int pageSize);

    void updateCurrentUpLoadedSuccessGPSRescords(List<GPSTransferIniBean> gpsTransferIniBeans);

    void updateCurrentUploadedSuccessGPSImgRecords(List<GPSTransferIniBean> gpsTransferIniBeans);

    void upLoadGPSRecordDatas2UpStream(List<GPSTransferIniBean> gpsTransferIniBeans);

    int updateGPSImgPath2DBRecord(GPSTransferIniBean gpsTransferIniBean);

   // void batchInsertMonitorGPSDatas(List<GPSTransferIniBean> gpsTransferIniBeans);
}
