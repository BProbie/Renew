package com.probie.renew;

import com.probie.renew.System.FileSystem;
import com.probie.renew.System.NetworkSystem;
import com.probie.renew.System.ComputerSystem;

public class Renew {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static Renew INSTANCE;

    public void renew(String[] args) {
        if (args.length >= 2) {
            String fullFileUrl = args[0];
            String fullFilePath = args[1];
            boolean isOpen = args.length < 3 || Boolean.parseBoolean(args[3]);
            getFileSystem().download(fullFileUrl, fullFilePath);
            if (isOpen) {
                getComputerSystem().open(fullFilePath);
            }
        }
    }

    /**
     * 获取懒加载的单例工具类
     * */
    public NetworkSystem getNetworkSystem() {
        return NetworkSystem.getInstance();
    }

    public ComputerSystem getComputerSystem() {
        return ComputerSystem.getInstance();
    }

    public FileSystem getFileSystem() {
        return FileSystem.getInstance();
    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static Renew getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Renew();
        }
        return INSTANCE;
    }

}