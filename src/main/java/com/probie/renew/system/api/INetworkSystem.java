package com.probie.renew.system.api;

public interface INetworkSystem {

    /**
     * 信任连接
     * */
    void trustConnect();

    /**
     * 信任 SSL 证书
     * */
    void trustSSL();

}