package com.znjt.rpc;

import com.znjt.dao.beans.GPSTransferIniBean;
import com.znjt.service.GPSTransferService;

import java.util.List;
import java.util.Optional;

/**
 * Created by qiuzx on 2019-03-15
 * Company BTT
 * Depart Tech
 */
public class TransportClient {
    private TransporterClientProxy transporterClientProxy;

    public TransportClient(GPSTransferService localTransferService,String addr, int port, int max_batch_size){
        transporterClientProxy = new TransporterClientProxy(localTransferService,addr,port,max_batch_size);
        createShutdownHook();
    }

    public void uploadBigDataByRPC(List<GPSTransferIniBean> gpsTransferIniBeans){
        if(gpsTransferIniBeans!=null&&gpsTransferIniBeans.size()>0){
            transporterClientProxy.transferData2Server(gpsTransferIniBeans);
        }
    }


    /**
     * 注册jvm关闭时释放线程资源的回调
     */
    private void createShutdownHook(){
        //程序退出时关闭资源
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            release();
        }));
    }

    /**
     * 关闭线程资源
     */
    private void release(){
        Optional.ofNullable(transporterClientProxy).ifPresent(proxy->{
            proxy.release();
        });
    }
}
