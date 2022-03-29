package com.scaffold.client.helper;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author jingletian
 * @date 2022/3/29 11:42
 */
public class LocalHelper {

    /**
     * 从本地文件中获取配置文件
     * @param filePath 配置文件的路径
     * @return 结果
     */
    public static String getContent(Path filePath) {
        try {
            StringJoiner stringJoiner=new StringJoiner("","","\n");
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath.toFile()), "utf-8"));
            String t;
            while ((t = reader.readLine()) != null) {
                stringJoiner.add(t);
            }
            return stringJoiner.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将配置保存到本地文件中
     * @param filePath
     * @param lines
     */
    public static void save(Path filePath,List<String> lines){
        try {
            BufferedWriter result = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath.toFile()), "utf-8"));
            for (int i = 0; i < lines.size(); i++) {
                result.write(lines.get(i));
                result.flush();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
