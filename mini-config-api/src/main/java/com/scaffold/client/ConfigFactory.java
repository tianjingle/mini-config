package com.scaffold.client;

import com.scaffold.client.core.MyCoinfg;
import com.scaffold.client.helper.MyTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author jingletian
 * @date 2022/3/29 10:40
 */
public class ConfigFactory {

    /**
     * 配置的地址
     */
    public static String env;

    private static Properties properties=new Properties();

    /**
     * 项目中的配置文件
     */
    private static String PROJECT_INFO="project.properties";


    private static String ENV="env";

    public static String localConfigPath="conf";

    public static String configPath;


    /**
     * 给缓存起来
     */
    public static final Map<String,IConfig> items=new ConcurrentHashMap<String,IConfig>();



    /**
     * 初始化的时候先加载配置中心的配置，如果读取不到再从本地缓存中读取配置文件
     */
    static {
        //拿到配置路劲
        if (configPath==null) {
            configPath = scanRootPath(localConfigPath);
        }
        doParseEnv();
        Thread thread=new Thread(new MyTask());
        Runtime.getRuntime().addShutdownHook(thread);
        thread.start();
    }

    /**
     * 获取部署的环境
     * @return
     */
    private static void doParseEnv() {
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROJECT_INFO);
            properties.load(inputStream);
            if (properties!=null){
                env = properties.getProperty(ENV);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String scanRootPath(String resource) {
        try {
            Enumeration<URL> ps = Thread.currentThread().getContextClassLoader().getResources(resource);
            while (ps.hasMoreElements()) {
                final URL url = ps.nextElement();
                System.out.println(url);
                //不读取jar包里面的/autoconf目录
                final String s = url.toString();
                if (s.startsWith("jar:file:")) {
                    System.out.println("found project  at " + s);
                } else if (s.startsWith("file:/")) {
                    String os_name = System.getProperty("os.name");
                    if (os_name != null && os_name.toLowerCase().contains("windows")) {
                        return s.substring(6);
                    } else {
                        return s.substring(5);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("scan root path"+e);
        }
        return null;
    }

    /**
     * 获取配置
     * @param name 配置名称
     * @param iChangeListener 监听器
     * @return 配置类
     */
    public static IConfig getConfig(String name, IChangeListener iChangeListener,boolean userLocal){
        if (!items.containsKey(name)){
            IConfig iConfig=new MyCoinfg(env,name,userLocal);
            //初始化
            iConfig.init();
            //添加监听器
            iConfig.addListener(iChangeListener);
            items.put(name,iConfig);
        }else {
            //返回配置
            return items.get(name);
        }
        return null;
    }
}
