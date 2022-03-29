package com.scaffold.service;

import com.scaffold.client.request.ConfigVo;
import com.scaffold.client.request.FetchQueryVo;
import com.scaffold.dao.model.ConfigModel;
import com.scaffold.utils.MyResult;

/**
 * @Author tianjl
 * @Date 2022/3/21 15:35
 * @Discription disc
 */
public interface MyConfigService {

    /**
     * 获取配置
     * @param env
     * @param name
     * @return
     */
    MyResult fetchConfigByEnv(String env, String name);

    /**
     * 添加配置
     * @param fetchQueryVo
     * @return
     */
    MyResult addConfig(ConfigModel fetchQueryVo);
}
