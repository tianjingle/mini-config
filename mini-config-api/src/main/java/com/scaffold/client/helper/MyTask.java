package com.scaffold.client.helper;

import com.scaffold.client.ConfigFactory;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author jingletian
 * @date 2022/3/29 14:05
 */
public class MyTask implements Runnable{

    public static String md5(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[]byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * pool线程
     */
    @Override
    public void run() {
        while (true) {
            ConfigFactory.items.forEach((key, value) -> {
                String result = null;
                try {
                    result = RemoteHelper.poll(ConfigFactory.env, key);
                    System.out.println(result);
                    String md5 = md5(result);
                    //如果配置发生变化，就重新拉去配置，装载到缓存，然后保存到本地文件中。
                    if (!md5.equals(value.getMd5())) {
                        value.loadConfigRemote();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            try {
                Thread.sleep(2000);
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
}
