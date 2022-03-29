package com.scaffold.service.impl;

import com.scaffold.client.request.ConfigVo;
import com.scaffold.dao.ConfigModelMapper;
import com.scaffold.dao.model.ConfigModel;
import com.scaffold.service.MyConfigService;
import com.scaffold.utils.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @Author tianjl
 * @Date 2022/3/21 15:36
 * @Discription disc
 */
@Service
public class MyConfigServiceImpl implements MyConfigService {

    @Autowired
    private ConfigModelMapper configModelMapper;
    /**
     * 这块查redis，查数据库。流量打到redis上。
     * @param env
     * @param name
     * @return
     */
    @Override
    public MyResult fetchConfigByEnv(String env, String name) {
        List<ConfigModel> configModels = configModelMapper.findByName(env,name);
        if (configModels.size()>0){
            return MyResult.success(configModels.get(0).getTxt());
        }
        return MyResult.success("");
    }


    /**
     * 添加一个配置
     * @param configModel
     * @return
     */
    @Override
    public MyResult addConfig(ConfigModel configModel) {
        List<ConfigModel> configModels = configModelMapper.findByName(configModel.getEnv(),configModel.getName());
        if (configModels.size()>0){
            configModelMapper.modify(configModel.getId(),configModel.getTxt());
            return MyResult.success("");
        }else {
            configModel.setId(UUID.randomUUID().toString());
            configModelMapper.insert(configModel);
            return MyResult.success("");
        }
    }
}
