package com.scaffold;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @Author tianjl
 * @Date 2022/3/21 15:20
 * @Discription disc
 */

@SpringBootApplication
public class ConfigService {

    /**
     * 配置中心启动
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigService.class,args);
    }
}
