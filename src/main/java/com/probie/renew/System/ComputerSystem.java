package com.probie.renew.System;

import com.probie.renew.System.Interface.IComputerSystem;

public class ComputerSystem extends NetworkSystem implements IComputerSystem {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static ComputerSystem INSTANCE;

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static ComputerSystem getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ComputerSystem();
        }
        return INSTANCE;
    }

}