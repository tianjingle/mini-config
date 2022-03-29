package com.scaffold.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


/**
 * @Author tianjl
 * @Date 2021/8/16 11:05
 * @Discription disc
 */
@Configuration
public class MybatisConfig {


    @Bean(name = "dataBaseNoun")
    public SqlSessionFactoryBean getSqlSessionFactoryOne1() throws Exception {
        //xml和实体的映射
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(new MyDataSource(true));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.scaffold.dao.model");
        Resource[] resources = new Resource[]{
                new ClassPathResource("mybatis/ConfigModelMapper.xml")};
        sqlSessionFactoryBean.setMapperLocations(resources);
        return sqlSessionFactoryBean;
    }


    @Bean
    @ConditionalOnBean(name = "dataBaseNoun")
    public static MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.scaffold.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("dataBaseNoun");
        return mapperScannerConfigurer;
    }
}
