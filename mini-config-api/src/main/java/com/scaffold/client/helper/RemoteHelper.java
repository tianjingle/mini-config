package com.scaffold.client.helper;

import com.alibaba.fastjson.JSONObject;
import javafx.print.PageLayout;
import sun.net.www.http.HttpClient;

import javax.print.attribute.standard.MediaSize;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

/**
 * @author jingletian
 * @date 2022/3/29 11:20
 */
public class RemoteHelper {
    /**
     * poll路径
     */
    public static String POLL_URL="http://127.0.0.1:8888/mini-config/api/poll/";

    /**
     * pull路径
     */
    public static String PULL_URL="http://127.0.0.1:8888/pull";

    public static HttpClient client=null;
    /**
     * 获取路径
     */
    static {
        if (System.getProperties().contains("poll")){
            POLL_URL=System.getProperty("poll");
        }
        if (System.getProperties().contains("pull")){
            PULL_URL=System.getProperty("pull");
        }
    }




    /**
     * 将字符创转化为JSONObject对象
     * @param s string方法
     * @return jsonObject对象
     */
    private static JSONObject toJson(String s) {
        return JSONObject.parseObject(s);
    }

    public static String poll(String env,String fileName) throws IOException {
        String url = POLL_URL+env+"/"+ fileName;
        System.out.println(url);
        HttpURLConnection conn =  (HttpURLConnection) (new URL(url).openConnection());
        conn.setReadTimeout(5000);
        conn.setConnectTimeout(500000);
        conn.setRequestMethod("GET");
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 AppleWebKit/537.36 wenwen");
        conn.setRequestProperty("Accept", "*/*");
        InputStream stream = null;
        stream = getInputStream(conn);
        BufferedReader in = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        StringBuilder sbd = new StringBuilder(4096);
        String s;
        while ((s = in.readLine()) != null) {
            sbd.append(s).append('\n');
        }
        System.out.println(sbd.toString());
        JSONObject jsonObject=toJson(sbd.toString());
        return jsonObject.getString("data");
    }

    private static InputStream getInputStream(HttpURLConnection conn) throws IOException {
        return conn.getInputStream();
    }

    public static String pull(String env,String fileName) throws IOException {
        return poll(env,fileName);
    }
}
