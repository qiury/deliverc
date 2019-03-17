package com.znjt.service;

import com.znjt.dao.beans.GPSTransferIniBean;
import com.znjt.dao.impl.GPSTransferDao;
import com.znjt.dao.mapper.GPSTransferBeanMapper;
import com.znjt.datasource.enhance.EnhanceDbUtils;
import com.znjt.datasource.enhance.EnhanceMapperFactory;
import com.znjt.exs.DBException;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
        dao.upLoadGPSRecordDatas2UpStream(dbname,gpsTransferIniBeans);
    }

}
