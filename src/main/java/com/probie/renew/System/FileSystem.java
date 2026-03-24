package com.probie.renew.System;

import java.io.IOException;
import java.net.URISyntaxException;
import com.probie.renew.System.Interface.IFileSystem;

public class FileSystem extends ComputerSystem implements IFileSystem {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static FileSystem INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static FileSystem getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FileSystem();
        }
        return INSTANCE;
    }

    /**
     * 重写下载方法
     * 使其适配网络加速的特殊环境
     * */
    @Override
    public boolean download(String uriPath, String fullFilePath) {
        try {
            return IFileSystem.super.download(uriPath, fullFilePath);
        } catch (URISyntaxException | IOException ignored) {
            trustConnect();
            try {
                return IFileSystem.super.download(uriPath, fullFilePath);
            } catch (URISyntaxException | IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

}