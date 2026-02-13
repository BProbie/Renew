package com.probie.renew.Renew.Interface;

import com.probie.renew.Renew.Renew;
import com.probie.renew.System.FileSystem;
import com.probie.renew.System.NetworkSystem;
import com.probie.renew.System.ComputerSystem;

public interface IRenew {

    /**
     * 更新逻辑
     * @param args main 函数中的 args 参数数组
     * */
    void renew(String[] args);

    /**
     * 更新逻辑
     * @return 是否更新成功
     * */
    boolean renew();

    /**
     * 获取懒加载的单例工具类
     * */
    NetworkSystem getNetworkSystem();
    ComputerSystem getComputerSystem();
    FileSystem getFileSystem();

    /**
     * set & get
     * */
    Renew setJavaFilePath(String javaFilePath);
    String getJavaFilePath();

    Renew setRenewFilePath(String renewFilePath);
    String getRenewFilePath();

    Renew setFullFileUrl(String fullFileUrl);
    String getFullFileUrl();

    Renew setFullFilePath(String fullFilePath);
    String getFullFilePath();

    Renew setIsOpen(boolean isOpen);
    boolean getIsOpen();

}