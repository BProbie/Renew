package com.probie.renew.Renew;

import java.io.File;
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
     * 获取懒加载的类单例对象
     * */
    public synchronized static Renew getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Renew();
        }
        return INSTANCE;
    }

    /**
     * Help 帮助
     * */
    public String[] help = new String[] {
            "java -jar Renew.jar help",
            "java -jar Renew.jar [FullFileUrl] [FullFilePath]",
            "java -jar Renew.jar [FullFileUrl] [FullFilePath] [IsOpen(True|False)]",
    };

    /**
     * 路径参数
     * */
    public String javaFilePath = "java";
    public String renewFilePath = "Renew.jar";
    public String fullFileUri = "https://github.com/BProbie/Renew/raw/refs/heads/master" + "/" + "res" + "/" + "Renew.jar";
    public String fullFilePath = getComputerSystem().getHere() + File.separator + "Renew.exe";
    public boolean isOpen = true;

    @Override
    public void renew(String[] args) {
        if (args.length >= 2) {
            String fullFileUri = args[0];
            String fullFilePath = args[1];
            boolean isOpen = args.length < 3 || Boolean.parseBoolean(args[2]);
            getFileSystem().download(fullFileUri, fullFilePath);
            if (isOpen) {
                getComputerSystem().open(fullFilePath);
            }
        } else {
            for (String s : help) System.out.println(s);
        }
    }

    @Override
    public boolean renew() {
        String systemName = getComputerSystem().getSystemName().toLowerCase();
        if (systemName.contains("windows")) {
            String command = "cmd"+ " " + "/c" + " "
                    + getJavaFilePath() + " " + "-jar" + " "
                    + getRenewFilePath() + " "
                    + getFullFileUri() + " "
                    + getFullFilePath() + " "
                    + getIsOpen();
            if (getComputerSystem().runCommand(command, false) != 0) {
                return getComputerSystem().runCommand(command, true) == 0;
            }
            return true;
        }
        return false;
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

    @Override
    public Renew setJavaFilePath(String javaFilePath) {
        this.javaFilePath = javaFilePath;
        return this;
    }

    @Override
    public String getJavaFilePath() {
        return javaFilePath;
    }

    @Override
    public Renew setRenewFilePath(String renewFilePath) {
        this.renewFilePath = renewFilePath;
        return this;
    }

    @Override
    public String getRenewFilePath() {
        return renewFilePath;
    }

    @Override
    public Renew setFullFileUri(String fullFileUrl) {
        this.fullFileUri = fullFileUrl;
        return this;
    }

    @Override
    public String getFullFileUri() {
        return fullFileUri;
    }

    @Override
    public Renew setFullFilePath(String fullFilePath) {
        this.fullFilePath = fullFilePath;
        return this;
    }

    @Override
    public String getFullFilePath() {
        return fullFilePath;
    }

    @Override
    public Renew setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
        return this;
    }

    @Override
    public boolean getIsOpen() {
        return isOpen;
    }

}