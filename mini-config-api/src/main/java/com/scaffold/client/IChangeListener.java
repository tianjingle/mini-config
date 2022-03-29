package com.scaffold.client;

/**
 * @author jingletian
 * @date 2022/3/29 10:34
 */
public interface IChangeListener {
    /**
     * 数据改变
     * @param iConfig
     */
    void dataChanged(IConfig iConfig);
}
