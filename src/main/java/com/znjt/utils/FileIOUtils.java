package com.znjt.utils;

import com.znjt.exs.FileIOException;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Created by qiuzx on 2019-03-15
 * Company BTT
 * Depart Tech
 */
public class FileIOUtils {

    private FileIOUtils(){

    }
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

    /**
     * 为图像创建相对路径
     * @return
     */
    public static String createRelativePath4Image(String imageName){
        StringBuilder sb = new StringBuilder("/");
        sb.append(getRandomFlag());
        sb.append("/");
        sb.append(getRandomFlag());
        sb.append("/").append(imageName).append(".jpg");
        return sb.toString();
    }

    /**
     * 生成随机数
     * @return
     */
    private static String getRandomFlag(){
        return String.format("%02x",ThreadLocalRandom.current().nextInt(0,128));
    }

    /**
     * 初始化文件存储目录12**128个
     */
    public static void init_fs_dirs(String base_dir){
        String dirs;
        File dir = null;
        for(int i=0;i<128;i++){
            for(int j=0;j<128;j++){
                dirs = base_dir+String.format("%02x",i)+"/"+String.format("%02x",j);
                dir = new File(dirs);
                if(!dir.exists()){
                    boolean res = dir.mkdirs();
                    System.err.println("创建系统文件夹["+dirs+"] " + (res?"success":"failure"));
                }
            }
        }
    }
}
