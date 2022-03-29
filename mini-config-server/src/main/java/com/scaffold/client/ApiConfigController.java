package com.scaffold.client;

import com.scaffold.service.MyConfigService;
import com.scaffold.utils.MyResult;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Author tianjl
 * @Date 2022/3/21 15:26
 * @Discription disc
 */
@RestController
@RequestMapping(value = "/mini-config/api/")
public class ApiConfigController {

    private MyConfigService myConfigService;

    /**
     * set
     * @param myConfigService
     */
    private ApiConfigController(MyConfigService myConfigService){
        this.myConfigService=myConfigService;
    }
    /**
     * 通过环境获取配置
     * @param env 配置的环境
     * @param name 配置文件的名称
     * @return 配置的结果
     */
    @GetMapping(value = "/poll/{env}/{name}")
    public MyResult fetchConfig(@PathVariable(name = "env") String env,@PathVariable(name = "name") String name){
        if (StringUtils.hasText(env)&& org.springframework.util.StringUtils.hasText(name)){
            return myConfigService.fetchConfigByEnv(env,name);
        }
        return MyResult.error("",null);
    }

}
