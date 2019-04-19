package com.znjt.utils;

import com.znjt.exs.FileIOException;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by qiuzx on 2019-03-15
 * Company BTT
 * Depart Tech
 */
public class FileIOUtils {
    private static final AtomicInteger LOOPER = new AtomicInteger();
    //100k
    private static int BUFFER_SIZE = 1024*1024;
    private FileIOUtils(){

    }
    /**
     * 从指定位置读取二进制文件
     * @return
     */
    public static byte[] getImgBytesDataFromPath(String path){
        byte[] bytes = null;
        if(path!=null){
            //测试路径
            // path="/Users/qiuzx/IdeaProjects/qiuzx/deliverc/imgs/ai.jpg";
            try(InputStream is = new BufferedInputStream(new FileInputStream(path),BUFFER_SIZE)){
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
        try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path),BUFFER_SIZE)) {
            IOUtils.write(imgs,bos);
        }catch (Exception ex){
            throw new FileIOException(ex);
        }
    }

    /**
     * 删除文件
     * @param abs_path
     */
    public static void deleteFile(String abs_path){
        File file = new File(abs_path);
        if(file.exists()){
            file.delete();
        }
    }

    /**
     * 为图像创建相对路径,相对fs目录
     * @return
     */
    public static String createRelativePath4Image(String imageName){
        StringBuilder sb = new StringBuilder(File.separator+"fs"+File.separator);
        sb.append(getRandomFlag());
        sb.append(File.separator);
        sb.append(getRandomFlag());
        sb.append(File.separator).append(imageName).append(getLoopNumFlag()).append(".jpg");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.err.println(createRelativePath4Image("01"));
        System.err.println(CommonFileUitls.getProjectPath());
    }

    /**
     * 获取一个2位循环数字
     * @return
     */
    private static String getLoopNumFlag(){
        int res = LOOPER.incrementAndGet();
        if(res==99){
            LOOPER.set(1);
        }
        return String.format("%02x",res);
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
    public static void init_fs_dirs(String base_dir,String relative_dir){
        String dirs;
        File dir = null;
        for(int i=0;i<128;i++){
            for(int j=0;j<128;j++){
                dirs = base_dir+relative_dir+String.format("%02x",i)+"/"+String.format("%02x",j);
                dir = new File(dirs);
                if(!dir.exists()){
                    boolean res = dir.mkdirs();
                    System.err.println("创建系统文件夹["+dirs+"] " + (res?"success":"failure"));
                }
            }
        }
    }
}
