package com.probie.renew.renew;

import java.io.File;
import com.probie.renew.renew.api.IRenew;
import com.probie.renew.system.FileSystem;
import com.probie.renew.system.ComputerSystem;

public class Renew implements IRenew {

    public final String NAME = "RENEW";
    public final String VERSION = "v1.0.0";

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static Renew INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
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
            "java -jar Renew.jar version",
            "java -jar Renew.jar [FullFileUrl] [FullFilePath]",
            "java -jar Renew.jar [FullFileUrl] [FullFilePath] [IsOpen(True|False)]",
    };

    /**
     * 路径参数
     * */
    public String javaFilePath = "java";
    public String renewFilePath = "Renew.jar";
    public String fullFileUri = "https://github.com/BProbie/Renew/releases/download/v1.0.0/Renew.jar";
    public String fullFilePath = ComputerSystem.getInstance().getHere() + File.separator + fullFileUri.split("/")[fullFileUri.split("/").length - 1];
    public boolean isOpen = true;

    @Override
    public void renew(String[] args) {
        if (args.length >= 2) {
            String fullFileUri = args[0];
            String fullFilePath = args[1];
            boolean isOpen = args.length < 3 || Boolean.parseBoolean(args[2]);
            if (FileSystem.getInstance().download(fullFileUri, fullFilePath)) {
                System.out.println("Renew Success");
            } else {
                System.out.println("Renew Failed");
            }
            if (isOpen) {
                ComputerSystem.getInstance().open(fullFilePath);
            }
        } else if (args.length == 1 && args[0].equalsIgnoreCase("version")) {
            System.out.printf("%s-%s\n", NAME, VERSION);
        } else {
            for (String s : help) System.out.println(s);
        }
    }

    @Override
    public boolean renew() {
        String systemName = ComputerSystem.getInstance().getSystemName().toLowerCase();
        if (systemName.contains("windows")) {
            String command = "cmd"+ " " + "/c" + " "
                    + getJavaFilePath() + " " + "-jar" + " "
                    + getRenewFilePath() + " "
                    + getFullFileUri() + " "
                    + getFullFilePath() + " "
                    + getIsOpen();
            if (ComputerSystem.getInstance().runCommand(command, false) != 0) {
                return ComputerSystem.getInstance().runCommand(command, true) == 0;
            }
            return true;
        }
        return false;
    }

    @Override
    public int turnVersionToNumber(Object version) {
        String[] versions = String.valueOf(version).toLowerCase().replace("v", "").split("\\.");
        int number = 0;
        for (int i = 0; i < versions.length; i++) {
            number += (int) (Integer.parseInt(versions[i]) * Math.pow(10, versions.length - (i + 1)));
        }
        return number;
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