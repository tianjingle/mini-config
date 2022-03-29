package com.scaffold.utils;


/**
 * @Author tianjl
 * @Date 2022/3/21 15:28
 * @Discription disc
 */
public class MyResult {

    private int code;

    private String msg;

    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public MyResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static MyResult success(Object data){
        return new MyResult(200,"",data);
    }

    public static MyResult success(String msg, Object data){
        return new MyResult(200,msg,data);
    }

    public static MyResult error(String msg, Object data){
        return new MyResult(400,msg,data);
    }
}
