package com.znjt.rpc;

import com.znjt.proto.GPSRecord;

/**
 * Created by qiuzx on 2019-03-20
 * Company BTT
 * Depart Tech
 * @author qiuzx
 */
public class ImageProcessorResult {
    private GPSRecord gpsRecord;
    private boolean ops_res = false;
    private boolean img_err = false;
    private boolean persistent = false;
    private String filePath = null;
    private String absPath = null;
    private String relPath = null;


    public ImageProcessorResult(){}
    public ImageProcessorResult(GPSRecord gpsRecord, boolean ops_res, boolean img_err, boolean persistent,String filePath) {
        this.gpsRecord = gpsRecord;
        this.ops_res = ops_res;
        this.img_err = img_err;
        this.persistent = persistent;
        this.filePath = filePath;
    }

    public GPSRecord getGpsRecord() {
        return gpsRecord;
    }

    public void setGpsRecord(GPSRecord gpsRecord) {
        this.gpsRecord = gpsRecord;
    }

    public boolean isOps_res() {
        return ops_res;
    }

    public void setOps_res(boolean ops_res) {
        this.ops_res = ops_res;
    }

    public boolean isImg_err() {
        return img_err;
    }

    public void setImg_err(boolean img_err) {
        this.img_err = img_err;
    }

    public boolean isPersistent() {
        return persistent;
    }

    public void setPersistent(boolean persistent) {
        this.persistent = persistent;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getRelPath() {
        return relPath;
    }

    public void setRelPath(String relPath) {
        this.relPath = relPath;
    }

    public String getAbsPath() {
        return absPath;
    }

    public void setAbsPath(String absPath) {
        this.absPath = absPath;
    }
}
