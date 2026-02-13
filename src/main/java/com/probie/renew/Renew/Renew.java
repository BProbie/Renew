package com.probie.renew.Renew;

import com.probie.renew.System.FileSystem;
import com.probie.renew.System.NetworkSystem;
import com.probie.renew.System.ComputerSystem;
import com.probie.renew.Renew.Interface.IRenew;

public class Renew implements IRenew {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static Renew INSTANCE;

    /**
     * Help 帮助
     * */
    public String[] help = new String[] {
            "java -jar Renew.jar help",
            "java -jar Renew.jar [FullFileUrl] [FullFilePath]",
            "java -jar Renew.jar [FullFileUrl] [FullFilePath] [IsOpen(True|False)]",
    };

    @Override
    public void renew(String[] args) {
        if (args.length >= 2) {
            String fullFileUrl = args[0];
            String fullFilePath = args[1];
            boolean isOpen = args.length < 3 || Boolean.parseBoolean(args[2]);
            getFileSystem().download(fullFileUrl, fullFilePath);
            if (isOpen) {
                getComputerSystem().open(fullFilePath);
            }
        } else {
            for (String s : help) System.out.println(s);
        }
    }

    @Override
    public NetworkSystem getNetworkSystem() {
        return NetworkSystem.getInstance();
    }

    @Override
    public ComputerSystem getComputerSystem() {
        return ComputerSystem.getInstance();
    }

    @Override
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