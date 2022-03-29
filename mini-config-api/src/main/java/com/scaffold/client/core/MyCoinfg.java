package com.scaffold.client.core;

import com.scaffold.client.ConfigFactory;
import com.scaffold.client.IChangeListener;
import com.scaffold.client.IConfig;
import com.scaffold.client.helper.LocalHelper;
import com.scaffold.client.helper.MyTask;
import com.scaffold.client.helper.RemoteHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jingletian
 * @date 2022/3/29 11:00
 */
public class MyCoinfg implements IConfig {

    /**
     * 配置的环境
     */
    private String env;

    /**
     *  配置文件的name
     */
    private String name;

    /**
     * 老的配置
     */
    private String oldMd5;

    /**
     * 配置文件的装载
     */
    private Map<String,String> items=new HashMap<>();

    /**
     * 所有的配置
     */
    private List<String> lines = new ArrayList<>();

    /**
     * 配置监听器，一个配置可能在多个地方使用到
     */
    private Set listeners= Collections.newSetFromMap(new ConcurrentHashMap<IChangeListener,Boolean>());

    /**
     * 是否使用本地缓存文件
     */
    private boolean useLocal = false;

    /**
     * 初始化
     * @param env
     * @param name
     * @param useLocal
     */
    public MyCoinfg(String env,String name, boolean useLocal) {
        this.env=env;
        this.name = name;
        this.useLocal = useLocal;
    }

    /**
     * 获取String配置
     * @param key
     * @return
     */
    @Override
    public String getString(String key) {
        if (items.containsKey(key)){
            return items.get(key);
        }
        return null;
    }

    /**
     * 获取所有配置
     * @param key
     * @return
     */
    @Override
    public List<String> getLines(String key) {
        return lines;
    }

    @Override
    public void addListener(IChangeListener iChangeListener) {
        if (!listeners.contains(iChangeListener)) {
            //量子管道
            iChangeListener.dataChanged(this);
            listeners.add(iChangeListener);
        }
    }

    /**
     * 先从
     */
    public void init(){
        //初次使用是否走本地配置
        if (useLocal){
            loadConfigLocal();
        }else{
            loadConfigRemote();
        }
    }

    @Override
    public String getMd5() {
        return oldMd5;
    }

    /***
     * 将配置转化到配置类中
     * @param txt
     */
    private void doParseTxt2Items(String txt) {
        if (txt==null||txt.length()==0){
            return;
        }
        oldMd5= MyTask.md5(txt);
        String[] values=txt.split("\n");
        for (int i = 0; i < values.length; i++) {
            if (values[i].length()>0&&!values[i].trim().startsWith("#")&&values[i].contains("=")){
                String[] result=values[i].split("=");
                if (result.length==2){
                    items.put(result[0],result[1]);
                    lines.add(values[i]);
                }
            }
        }

    }

    /**
     * 加载远程配置文件
     */
    public void loadConfigRemote() {
        String txt = null;
        try {
            txt = RemoteHelper.pull(env,name);
            doParseTxt2Items(txt);
            //存储到本地
            Path path= Paths.get(ConfigFactory.configPath, name);
            LocalHelper.save(path,lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 加载本地配置文件
     */
    private void loadConfigLocal() {
        Path path= Paths.get(ConfigFactory.configPath, name);
        String txt = LocalHelper.getContent(path.toAbsolutePath());
        doParseTxt2Items(txt);
    }
}
