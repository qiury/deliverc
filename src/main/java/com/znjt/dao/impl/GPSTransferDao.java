package com.znjt.dao.impl;

import com.znjt.dao.beans.GPSTransferIniBean;
import com.znjt.dao.mapper.GPSTransferBeanMapper;
import com.znjt.datasource.enhance.EnhanceDbUtils;
import com.znjt.datasource.enhance.EnhanceMapperFactory;
import com.znjt.exs.DBException;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by qiuzx on 2019-03-15
 * Company BTT
 * Depart Tech
 */
public class GPSTransferDao {
    public List<GPSTransferIniBean> findUnUpLoadGPSRecordDatas(String dbname,int pageSize) {
        List<GPSTransferIniBean> recordDatas = null;
        try {
            SqlSession sqlSession = EnhanceMapperFactory.getMultiSqlSession(dbname, true);
            GPSTransferBeanMapper mapper = EnhanceMapperFactory.createMapper(GPSTransferBeanMapper.class, sqlSession);
            recordDatas = mapper.findUnUpLoadGPSRecordDatas(pageSize);
        } catch (Exception e) {
            new DBException(e,"查询未上传的GPS记录出现异常");
        } finally {
            EnhanceDbUtils.closeSession();
        }
        return recordDatas;
    }

    public List<GPSTransferIniBean> findUnUpLoadGPSImgDatas(String dbname,int pageSize) {
        List<GPSTransferIniBean> recordDatas = null;
        try {
            SqlSession sqlSession = EnhanceMapperFactory.getMultiSqlSession(dbname, true);
            GPSTransferBeanMapper mapper = EnhanceMapperFactory.createMapper(GPSTransferBeanMapper.class, sqlSession);
            recordDatas = mapper.findUnUpLoadGPSImgDatas(pageSize);
        } catch (Exception e) {
            new RuntimeException("查询未上传图像的GPS记录出现异常",e);
        } finally {
            EnhanceDbUtils.closeSession();
        }
        return recordDatas;
    }

    public void updateCurrentUpLoadedSuccessGPSRescords(String dbname,List<GPSTransferIniBean> gpsTransferIniBeans) {
        try {
            SqlSession sqlSession = EnhanceMapperFactory.getMultiSqlSession(dbname, false);
            GPSTransferBeanMapper mapper = EnhanceMapperFactory.createMapper(GPSTransferBeanMapper.class, sqlSession);
            mapper.updateCurrentUpLoadedSuccessGPSRescords(gpsTransferIniBeans);
            sqlSession.commit();
        } catch (Exception e) {
            new DBException(e,"更新已经上传的GPS记录状态出现异常");
        } finally {
            EnhanceDbUtils.closeSession();
        }
    }

    public void updateCurrentUploadedSuccessGPSImgRecords(String dbname,List<GPSTransferIniBean> gpsTransferIniBeans) {
        List<GPSTransferIniBean> recordDatas = null;
        try {
            SqlSession sqlSession = EnhanceMapperFactory.getMultiSqlSession(dbname, false);
            GPSTransferBeanMapper mapper = EnhanceMapperFactory.createMapper(GPSTransferBeanMapper.class, sqlSession);
            mapper.updateCurrentUploadedSuccessGPSImgRecords(gpsTransferIniBeans);
            sqlSession.commit();
        } catch (Exception e) {
            new DBException(e,"更新已经上传的GPS图像状态出现异常");
        } finally {
            EnhanceDbUtils.closeSession();
        }
    }

    public void upLoadGPSRecordDatas2UpStream(String dbname,List<GPSTransferIniBean> gpsTransferIniBeans) {
        try {
            SqlSession sqlSession = EnhanceMapperFactory.getMultiSqlSession(dbname, false, ExecutorType.BATCH);
            GPSTransferBeanMapper mapper = EnhanceMapperFactory.createMapper(GPSTransferBeanMapper.class, sqlSession);
            mapper.upLoadGPSRecordDatas2UpStream(gpsTransferIniBeans);
            sqlSession.commit();
        } catch (Exception e) {
            new DBException(e,"更新已经上传的GPS图像状态出现异常");
        } finally {
            EnhanceDbUtils.closeSession();
        }
    }
}
