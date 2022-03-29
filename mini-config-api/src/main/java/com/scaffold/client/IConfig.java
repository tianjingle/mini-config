package com.scaffold.client;
import java.util.List;

public interface IConfig {

    /**
     * 获取配置
     * @param value
     * @return
     */
    String getString(String value);

    /**
     * 获取配置文本
     * @param value
     * @return
     */
    List<String> getLines(String value);

    /**
     * 添加监听器
     * @param IChangeListener
     */
    void addListener(IChangeListener IChangeListener);

    /**
     * 初始化
     */
    void init();

    /**
     * 旧md5值
     * @return
     */
    String getMd5();

    /**
     * 加载远程配置
     */
    void loadConfigRemote();
}
