package com.probie.renew.System.Interface;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public interface IComputerSystem {

    /**
     * 获取当前程序的路径
     * @return 当前程序的路径
     * */
    default String getHere() {
        return System.getProperty("user.dir");
    }

    /**
     * 打开文件
     * @param fullFilePath 完整本地文件路径
     * */
    default void open(String fullFilePath) {
        try {
            Desktop.getDesktop().open(new File(fullFilePath));
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    /**
     * 获取当前操作系统名称
     * Windows n
     * Linux
     * Mac
     * @return 操作系统名称
     * */
    default String getSystemName() {
        return System.getProperty("os.name");
    }

    /**
     * 获取当前操作系统架构
     * Windows amd64 | x86
     * Linux amd64 | aarch64
     * Mac arch64 | x86
     * @return 操作系统架构
     * */
    default String getSystemArch() {
        return System.getProperty("os.arch");
    }

    /**
     * 获取管理员运行指令
     * @param command 指令
     * @return 管理员指令
     * */
    default String getOpCommand(String command) {
        String systemName = getSystemName().toLowerCase();
        if (systemName.contains("windows")) {
            return String.format("powershell -Command \"Start-Process cmd -ArgumentList '/c %s C:\\Windows' -Verb RunAs\"", command.replace("\\","\\\\\\"));
        }
        return command;
    }

    /**
     * 运行命令行指令
     * @param command 要执行的命令
     * @return 返回从参数 0 代表执行成功
     * */
    default int runCommand(String command) {
        return runCommand(command, false);
    }

    /**
     * 运行命令行指令
     * @param command 要执行的命令
     * @param isOp 是否用管理员权限执行命令(Windows)
     * @return 返回从参数 0 代表执行成功
     * */
    default int runCommand(String command, boolean isOp) {
        try {
            if (isOp) {
                command = getOpCommand(command);
            }
            return new ProcessBuilder(command.split("\\s+")).start().waitFor();
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

}