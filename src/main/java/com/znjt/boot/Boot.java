package com.znjt.boot;

import com.znjt.CommonFileUitls;
import com.znjt.exs.ExceptionInfoUtils;
import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.regex.Pattern.*;

/**
 * Created by qiuzx on 2019-03-19
 * Company BTT
 * Depart Tech
 */
public class Boot {
    //目标数据源名称
    public static final String UPSTREAM_DBNAME = "slave";
    //源数据源名称
    public static final String DOWNSTREAM_DBNAME = "master";
    //普通记录批处理大小
    public static final int RECORD_BATCH_SIZE = 500;
    //图像记录批处理大小
    public static final int IMAGE_BATCH_SIZE = 50;

    private static boolean is_server = false;
    private static ClientBoot clientBoot;
    private static ExecutorService executorService = new ThreadPoolExecutor(1,1,0, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(1));

    /**
     * 系统启动
     * @param args
     */
    public static void main(String[] args) throws Exception{
        try {
            Properties properties = read_sys_cfg();
            read_opt_from_terminal(properties);
        }catch (Exception ex){
            System.err.println(ExceptionInfoUtils.getExceptionCauseInfo(ex));
            Runtime.getRuntime().halt(-1);
        }
    }

    /**
     * 根据命令行的输入选择启动客户端还是服务器端
     * @param properties
     */
    private static void read_opt_from_terminal(Properties properties){
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            //通过命令行的方式，覆盖配置文件指定的值
            String _port = null;//绑定的端口
            String _ip = null;//连接的端口
            System.err.println("欢迎使用数据同步系统，请您选择要启动的功能名称,请输入ser[ver]或cli[ent] ");
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if("exit".equals(str.trim())){
                    break;
                }
                //说明启动的是客户端程序
                if(str.trim().startsWith("cli")){
                    _ip = read_ip_from_cmd(scanner);
                    _port = read_port_from_cmd(scanner);
                    if(!_ip.isEmpty()){
                        properties.setProperty("upstrem.ip",_ip);
                    }
                    if(!_port.isEmpty()){
                        properties.setProperty("upstrem.port",_port);
                    }
                    //启动客户端程序
                    executorService.execute(()->{
                        clientBoot = new ClientBoot();
                        clientBoot.start_client_jobs(properties.getProperty("upstrem.ip"),Integer.parseInt(properties.getProperty("upstrem.port").trim()));
                    });
                    break;
                }else if(str.trim().startsWith("ser")){
                    is_server = true;
                    _port = read_port_from_cmd(scanner);
                    if(!_port.isEmpty()){
                        properties.setProperty("upstrem.port",_port);
                    }
                    System.err.println("start Server at " + properties.getProperty("upstrem.port"));
                    //启动服务端程序
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            ServerBoot.start_server(Integer.parseInt(properties.getProperty("upstrem.port").trim()));
                        }
                    });
                    break;
                }else{
                    System.err.println("输入的["+str+"]不合法，请您选择要启动的程序名称,请输入ser[ver]或cli[ent] ");
                }

            }
            start_monitor_user_controll(scanner);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(scanner!=null){
                scanner.close();
            }
        }
    }

    /**
     * 启动监听用户输入的请求
     */
    private static void start_monitor_user_controll(Scanner scanner){
        System.err.println("输入exit/quit/close退出程序 ");
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if(str.trim().equals("quit")||str.trim().equals("close")||str.trim().equals("exit")){
                break;
            }
            System.err.println("输入exit/quit/close退出程序 ");
        }
        System.err.println("系统即将退出，启动释放资源的操作..");
        relese_resource();
        System.err.println("资源释放完毕，退出系统 bye !");
    }

    private static void relese_resource(){
        try {
            //释放资源
            if(is_server){
                stopAndReleaseServerResource();
            }else{
                stopAndReleaseClientResource();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(executorService!=null){
                try {
                    if(!executorService.isShutdown()){
                        executorService.shutdown();;
                    }
                    if(!executorService.awaitTermination(15,TimeUnit.SECONDS)){
                        executorService.shutdownNow();
                    }else{
                        System.err.println("任务线程执行结束...");
                    }
                    if(!executorService.isShutdown()){
                        System.err.println("等待任务线程执行结束...超时，执行强制退出");
                        Runtime.getRuntime().halt(-1);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }


    /**
     * 客户端的线程是doemon的的，当前线程结束，子线程就结束
     */
    private static void stopAndReleaseClientResource(){
        if(clientBoot!=null){
            clientBoot.terminal_jobs();
        }
    }
    /**
     * 停止serve
     */
    private static void stopAndReleaseServerResource(){
        ServerBoot.stop_server();
    }


    /**
     * 从命令行读取Ip
     * @param scanner
     * @return
     */
    private static String read_ip_from_cmd(Scanner scanner){
        System.err.println("请输入服务器端的IP地址，默认请直接按回车");
        String _ip = null;
        while(scanner.hasNextLine()){
            _ip = scanner.nextLine();
            //用户输入了IP地址
            if(_ip.trim().length()!=0){
                if(!orIp(_ip)){
                    System.err.println("输入的服务器ip["+_ip+"]地址不合法，请检查后重新输入，默认请直接按回车");
                    continue;
                }
                break;
            }
            break;
        }
        return _ip.trim();
    }

    private static String read_port_from_cmd(Scanner scanner){
        System.err.println("请输入服务器端监听端口，默认请直接按回车");
        String _port = null;
        while(scanner.hasNextLine()){
            _port = scanner.nextLine();
            //用户输入了IP地址
            if(_port.trim().length()!=0){
                if(!isNumeric(_port)){
                    System.err.println("输入的服务器监听端口["+_port+"]不是数字，请检查后重新输入，默认请直接按回车");
                    continue;
                }
                break;
            }
            break;
        }
        return _port.trim();
    }
    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
    /**
     * 判断IP是否合法
     * @param ip
     * @return
     */
    public static boolean orIp(String ip) {
        if(ip == null || "".equals(ip)) {
            return false;
        }
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        return ip.matches(regex);
    }

    /**
     * 读取配置文件
     * @return
     */
    private static Properties read_sys_cfg(){
        Properties properties = null;
        try( InputStream is = Resources.getResourceAsStream("sys.properties")) {
            properties = new Properties();
            properties.load(is);
        }catch (FileNotFoundException ex){
            throw new RuntimeException("没有找到deliver.properties",ex);
        }catch (IOException e){
            throw new RuntimeException("IO异常",e);
        }
        return properties;
    }


}
