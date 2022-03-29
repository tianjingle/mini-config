package com.scaffold.client.request;

/**
 * @Author tianjl
 * @Date 2022/3/29 10:23
 * @Discription disc
 */
public class ConfigVo {
    private String env;

    private String txt;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return "ConfigVo{" +
                "env='" + env + '\'' +
                ", txt='" + txt + '\'' +
                '}';
    }
}
