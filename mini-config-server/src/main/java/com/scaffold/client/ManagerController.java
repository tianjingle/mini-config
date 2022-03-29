package com.scaffold.client;

import com.scaffold.client.request.FetchQueryVo;
import com.scaffold.dao.model.ConfigModel;
import com.scaffold.service.MyConfigService;
import com.scaffold.utils.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author tianjl
 * @Date 2022/3/21 15:46
 * @Discription disc
 */
@RestController
@RequestMapping(value = "/mini-config/manager/")
public class ManagerController {

    @Autowired
    private MyConfigService myConfigService;

    /**
     * 按条件获取所有配置
     * @param fetchQueryVo 配置条件
     * @return
     */
    @GetMapping(value = "/all")
    public MyResult all(@ModelAttribute FetchQueryVo fetchQueryVo){
        return myConfigService.fetchConfigByEnv(fetchQueryVo.getEnv(),fetchQueryVo.getName());
    }

    /**
     * 添加一个配置
     * @param configVo
     * @return
     */
    @PostMapping(value = "/add")
    public MyResult add(@RequestBody ConfigModel configVo){
        return myConfigService.addConfig(configVo);
    }
}
