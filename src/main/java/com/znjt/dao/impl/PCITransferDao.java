package com.znjt.dao.impl;

import com.znjt.dao.beans.PCITransferIniBean;
import com.znjt.dao.mapper.PCITransferBeanMapper;
import com.znjt.datasource.enhance.EnhanceDbUtils;
import com.znjt.datasource.enhance.EnhanceMapperFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by qiuzx on 2019-04-18
 * Company BTT
 * Depart Tech
 */
public class PCITransferDao {
    public List<PCITransferIniBean> findUnUpLoadPCIRecordDatas(String dbname, int pageSize) {
        List<PCITransferIniBean> recordDatas = null;
        try {
            SqlSession sqlSession = EnhanceMapperFactory.getMultiSqlSession(dbname, true);
            PCITransferBeanMapper mapper = EnhanceMapperFactory.createMapper(PCITransferBeanMapper.class, sqlSession);
            recordDatas = mapper.findUnUpLoadPCIRecordDatas(pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            new RuntimeException("查询未上传的PCI记录出现异常",e);
        } finally {
            EnhanceDbUtils.closeSession();
        }
        return recordDatas;
    }

    public void updateCurrentUpLoadedSuccessPCIRescords(String dbname,List<PCITransferIniBean> pciTransferIniBeans) {
        try {
            SqlSession sqlSession = EnhanceMapperFactory.getMultiSqlSession(dbname, false);
            PCITransferBeanMapper mapper = EnhanceMapperFactory.createMapper(PCITransferBeanMapper.class, sqlSession);
            mapper.updateCurrentUpLoadedSuccessPCIRescords(pciTransferIniBeans);
            sqlSession.commit();
        } catch (Exception e) {
            new RuntimeException("更新已经上传的PCI记录状态出现异常",e);
        } finally {
            EnhanceDbUtils.closeSession();
        }
    }


    public void upLoadPCIRecordDatas2UpStream(String dbname,List<PCITransferIniBean> pciTransferIniBeans) {
        try {
            SqlSession sqlSession = EnhanceMapperFactory.getMultiSqlSession(dbname, false, ExecutorType.BATCH);
            PCITransferBeanMapper mapper = EnhanceMapperFactory.createMapper(PCITransferBeanMapper.class, sqlSession);
            mapper.upLoadPICRecordDatas2UpStream(pciTransferIniBeans);
            sqlSession.commit();
        } catch (Exception e) {
            new RuntimeException("更新已经上传的IRI状态出现异常",e);
        } finally {
            EnhanceDbUtils.closeSession();
        }
    }
}
