package com.itheima.proxy.cglibProxy;

public class Client {
    public static void main(String[] args) {
        // 创建代理工厂对象
        ProxyFactory factory = new ProxyFactory();
        // 获取代理对象
        TrainStation proxyObject = factory.getProxyObject();
        // 调用代理对象中的sell方法卖票
        proxyObject.sell();
    }
}
