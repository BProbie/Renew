package com.probie.renew.renew.api;

import com.probie.renew.renew.Renew;

public interface IRenew {

    /**
     * 手动更新逻辑
     * @param args main 函数中的 args 参数数组
     * */
    void renew(String[] args);

    /**
     * 嵌入更新逻辑
     * @return 是否更新成功
     * */
    boolean renew();

    /**
     * 将版本号转化成数值
     * @param version 版本号
     * @return 版本号数值
     * */
    int turnVersionToNumber(Object version);

    /**
     * set & get
     * */
    Renew setJavaFilePath(String javaFilePath);
    String getJavaFilePath();

    Renew setRenewFilePath(String renewFilePath);
    String getRenewFilePath();

    Renew setFullFileUri(String fullFileUrl);
    String getFullFileUri();

    Renew setFullFilePath(String fullFilePath);
    String getFullFilePath();

    Renew setIsOpen(boolean isOpen);
    boolean getIsOpen();

}