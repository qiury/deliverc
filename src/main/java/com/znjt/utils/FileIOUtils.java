package com.znjt.utils;

import com.znjt.exs.FileIOException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by qiuzx on 2019-03-15
 * Company BTT
 * Depart Tech
 */
public class FileIOUtils {
    private FileIOUtils(){}
    /**
     * 从指定位置读取二进制文件
     * @return
     */
    public static byte[] getImgBytesDataFromPath(String path){
        byte[] bytes = null;
        if(path!=null){
            try(InputStream is = new FileInputStream(path)){
               // BufferedImage read = ImageIO.read(is);
                bytes = new byte[is.available()];
                IOUtils.read(is,bytes);
            }catch (Exception ex){
                throw new FileIOException(ex);
            }
        }
        return bytes;
    }

    /**
     * 将内存二进制数据写入disk
     * @param path
     * @param imgs
     */
    public static void saveBinaryImg2Disk(String path ,byte[] imgs){
        try(OutputStream os = new FileOutputStream(path)) {
            IOUtils.write(imgs,os);
        }catch (Exception ex){
            throw new FileIOException(ex);
        }
    }
}
