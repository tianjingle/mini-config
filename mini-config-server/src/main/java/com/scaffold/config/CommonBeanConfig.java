package com.scaffold.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * 默认的spring配置类
 */
@Configuration
public class CommonBeanConfig implements WebMvcConfigurer, AsyncConfigurer {



    /**
     * 1、 extends WebMvcConfigurationSupport
     * 2、重写下面方法;
     * setUseSuffixPatternMatch : 设置是否是后缀模式匹配，如“/user”是否匹配/user.*，默认真即匹配；
     * setUseTrailingSlashMatch : 设置是否自动后缀路径模式匹配，如“/user”是否匹配“/user/”，默认真即匹配；
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseTrailingSlashMatch(true);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        // 自定义 FastJson 配置
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 设置字符集
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
        // 剔除循环引用
        //fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        // 设置支持的 MediaType
        fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,
                MediaType.TEXT_PLAIN,
                MediaType.TEXT_HTML,
                MediaType.TEXT_XML,
                MediaType.APPLICATION_OCTET_STREAM));
        // 添加到 converters 中， 注意，添加到最开头
        converters.add(0, fastJsonHttpMessageConverter);
        // 再添加个string处理器
        converters.add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }
}
