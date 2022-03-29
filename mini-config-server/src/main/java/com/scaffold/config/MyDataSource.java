package com.scaffold.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.SQLException;

/**
 * @Author tianjl
 * @Date 2021/8/17 21:22
 * @Discription disc
 */
public class MyDataSource extends DruidDataSource {

    private static DruidDataSource noun;


    private boolean isNoun;

    public MyDataSource(boolean isNoun){
        this.isNoun=isNoun;
    }


    public static DruidDataSource getDruidDataSource(String url,String userName,String password) throws SQLException {
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(url);
        ds.setUsername(userName);
        ds.setPassword(password);
        try {
            ds.setFilters("stat,mergeStat,slf4j");
        } catch (Exception var18) {
        }
        ds.setMaxActive(50);
        ds.setInitialSize(1);
        ds.setMinIdle(1);
        ds.setMaxWait(60000);
        ds.setTimeBetweenEvictionRunsMillis(120000);
        ds.setMinEvictableIdleTimeMillis(300000);
        ds.setValidationQuery("SELECT 'x'");
        ds.setPoolPreparedStatements(true);
        ds.setMaxPoolPreparedStatementPerConnectionSize(30);
        ds.setTestWhileIdle(true);
        ds.setTestOnReturn(false);
        ds.setTestOnBorrow(false);
        ds.init();
        return ds;
    }


    public DruidPooledConnection getConnection() throws SQLException {
        //这块可以写具体得库选择逻辑，读库随机可以从用random方法。
        if (this.isNoun) {
            noun = getDruidDataSource("jdbc:mysql://localhost:3306/mini_config?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true", "root", "tianjingle");
        }else{
            noun = getDruidDataSource("jdbc:mysql://localhost:3306/mini_config?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true", "root", "tianjingle");
        }
        return noun.getConnection();
    }
}
