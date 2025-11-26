package com.probie.renew.System.Interface;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public interface IComputerSystem {

    /**
     * 打开文件
     * @param fullFilePath 完整本地文件路径
     * */
    default void open(String fullFilePath) {
        try {
            Desktop.getDesktop().open(new File(fullFilePath));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}