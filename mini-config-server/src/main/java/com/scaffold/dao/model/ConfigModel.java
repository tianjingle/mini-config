package com.scaffold.dao.model;

/**
 * @author jingletian
 * @date 2022/3/29 14:54
 */
public class ConfigModel {

    private String id;

    private String evn;

    private String name;

    private String txt;

    private String disc;

    public String getEnv() {
        return evn;
    }

    public void setEnv(String evn) {
        this.evn = evn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    @Override
    public String toString() {
        return "ConfigModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", txt='" + txt + '\'' +
                ", disc='" + disc + '\'' +
                '}';
    }
}
