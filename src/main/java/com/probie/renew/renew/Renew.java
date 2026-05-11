package com.probie.renew.renew;

import java.io.File;
import com.probie.renew.renew.api.IRenew;
import com.probie.renew.system.FileSystem;
import com.probie.renew.system.ComputerSystem;

public class Renew implements IRenew {

    /**
     * 工具信息
     * */
    public final String NAME = "RENEW";
    public final String VERSION = "v1.1.0";

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static Renew INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static Renew getInstance() {
        if (INSTANCE == null) {
            INSTANCE = Renew.builder("https://github.com/BProbie/Renew/releases/download/1.1.0/Renew.jar").build();
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
    public String javaFilePath;
    public String renewFilePath;
    public String fullFileUri;
    public String fullFilePath;
    public boolean isOpen;

    /**
     * 构造函数
     * @param renewBuilder Renew建造者
     * */
    private Renew(RenewBuilder renewBuilder) {
        setJavaFilePath(renewBuilder.javaFilePath);
        setRenewFilePath(renewBuilder.renewFilePath);
        setRenewFilePath(renewBuilder.fullFileUri);
        setRenewFilePath(renewBuilder.fullFilePath);
        setIsOpen(renewBuilder.isOpen);
    }

    /**
     * 建造者内部静态类
     * */
    public static class RenewBuilder {

        /**
         * 建造参数
         * */
        private String javaFilePath = "java";
        private String renewFilePath = "Renew.jar";
        private final String fullFileUri;
        private String fullFilePath;
        private boolean isOpen = true;

        /**
         * @param fullFileUri 必填 远程文件网址
         * */
        public RenewBuilder(String fullFileUri) {
            this.fullFileUri = fullFileUri;
            this.fullFilePath = ComputerSystem.getInstance().getHere() + File.separator + fullFileUri.split("/")[fullFileUri.split("/").length - 1];
        }

        /**
         * 构建 Renew
         * @return Renew 实例化对象
         * */
        public Renew build() {
            return new Renew(this);
        }

        /**
         * 设置参数
         * @param javaFilePath 选填 指定Java目录下的Java环境(默认为环境变量中定义的Java环境)
         * @return Renew 建造者
         * */
        public RenewBuilder javaFilePath(String javaFilePath) {
            this.javaFilePath = javaFilePath;
            return this;
        }

        /**
         * 设置参数
         * @param renewFilePath 选填 Renew更新工具的本地文件路径(默认为当前目录下的Renew.jar)
         * @return Renew 建造者
         * */
        public RenewBuilder renewFilePath(String renewFilePath) {
            this.renewFilePath = renewFilePath;
            return this;
        }

        /**
         * 设置参数
         * @param fullFilePath 选填 需要更新到的本地文件路径(默认为当前目录加上远程文件网站的最后一小段字符串)
         * @return Renew 建造者
         * */
        public RenewBuilder fullFilePath(String fullFilePath) {
            this.fullFilePath = fullFilePath;
            return this;
        }

        /**
         * 设置参数
         * @param isOpen 选填 更新完成后是否自动打开文件(默认为启用)
         * @return Renew 建造者
         * */
        public RenewBuilder isOpen(boolean isOpen) {
            this.isOpen = isOpen;
            return this;
        }

    }

    /**
     * 获取 Renew 建造者
     * @param fullFileUri 远程文件网址
     * @return Renew 建造者
     * */
    public static RenewBuilder builder(String fullFileUri) {
        return new RenewBuilder(fullFileUri);
    }

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
            number += (int) (Integer.parseInt(versions[i]) * Math.pow(Math.pow(10, 3), versions.length - (i + 1)));
        }
        return number;
    }

    @Override
    public boolean compareVersionWithSmallerVersion(Object version, Object smallerVersion) {
        return compareVersionWithBiggerVersion(smallerVersion, version);
    }

    @Override
    public boolean compareVersionWithBiggerVersion(Object version, Object biggerVersion) {
        String[] versions = String.valueOf(version).toLowerCase().replace("v", "").split("\\.");
        String[] biggerVersions = String.valueOf(biggerVersion).toLowerCase().replace("v", "").split("\\.");
        for (int i = 0; i < Math.min(versions.length, biggerVersions.length); i++) {
            if (Integer.parseInt(biggerVersions[i]) > Integer.parseInt(versions[i])) {
                return true;
            }
        }
        return biggerVersions.length > versions.length && Integer.parseInt(biggerVersions[versions.length]) > 0;
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