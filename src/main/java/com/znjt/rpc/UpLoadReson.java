package com.znjt.rpc;

/**
 * Created by qiuzx on 2019-04-11
 * Company BTT
 * Depart Tech
 */
public class UpLoadReson {
    //上传了多少字节
    private long totalBytes;
    //耗时 毫秒
    private long consumeMins;


    public UpLoadReson(long totalBytes, long consumeMins) {
        this.totalBytes = totalBytes;
        this.consumeMins = consumeMins;
    }

    public UpLoadReson() {

    }

    public long getTotalBytes() {
        return totalBytes;
    }

    public void setTotalBytes(long totalBytes) {
        this.totalBytes = totalBytes;
    }

    public long getConsumeMins() {
        return consumeMins;
    }

    public void setConsumeMins(long consumeMins) {
        this.consumeMins = consumeMins;
    }

    /**
     * 获取上传速度
     * @return
     */
    public long getSpeed(){
        if(consumeMins>0&&totalBytes>0){
            return totalBytes*1000/consumeMins;
        }
        return -1;
    }
}
