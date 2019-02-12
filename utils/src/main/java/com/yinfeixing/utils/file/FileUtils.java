package com.yinfeixing.utils.file;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUtils {

    public static void deleteFile(File... fileArr) {
        for (File file : fileArr) {
            if (file == null)
                return;
            if (file.exists()) {
                if (file.isFile()) {
                    file.delete();
                } else if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFile(files[i]);
                    }
                    file.delete();//删除文件夹
                }
            }
        }
    }
    /**
     * @Author:
     * @Description:获取带有https 格式的的文件流
     * @Date: 10:21 2018-08-16
     * @param fileUrl 文件地址
     * @return  返回文件流
     */
    public  static InputStream getFileByHttp(String fileUrl){
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(4*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            return  conn.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}
