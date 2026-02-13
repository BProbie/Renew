package com.probie.renew.System.Interface;

public interface INetworkSystem {

    /**
     * 信任连接
     * */
    void trustConnect();

    /**
     * 信任 SSL 连接
     * */
    void trustSSL();

}