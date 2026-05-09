package com.probie.renew.system.api;

import java.net.URI;
import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.FileOutputStream;
import java.net.URISyntaxException;

public interface IFileSystem {

    /**
     * 下载网络文件到本地
     * @param uriPath 网络文件网址
     * @param fullLocalFilePath 本地文件地址
     * @return 是否下载成功
     * */
    default boolean download(String uriPath, String fullLocalFilePath) throws URISyntaxException, IOException {
        URL url = new URI(uriPath).toURL();
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(1000 * 30);
        connection.setReadTimeout(1000 * 60);
        File localFile = new File(fullLocalFilePath);
        if (!localFile.getParentFile().exists()) localFile.getParentFile().mkdirs();
        try (InputStream inputStream = connection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(fullLocalFilePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return localFile.exists();
    }

}