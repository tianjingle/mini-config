package com.scaffold.web;

import com.scaffold.client.ConfigFactory;
import com.scaffold.client.IChangeListener;
import com.scaffold.client.IConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jingletian
 * @date 2022/3/29 14:38
 */
@RestController
public class ApiController {

    public static String key="tian";

    public static String lele="lele";

    static {
        ConfigFactory.getConfig(key, new IChangeListener() {
            @Override
            public void dataChanged(IConfig iConfig) {
                lele=iConfig.getString("lele");
            }
        },false);
    }

    @GetMapping(value = "/get")
    public String get(){
        return lele;
    }
}
