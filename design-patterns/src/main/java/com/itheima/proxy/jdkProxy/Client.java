package com.itheima.proxy.jdkProxy;

public class Client {
    public static void main(String[] args) {
        /*
            获取代理对象
            1.创建代理工厂对象
         */
        ProxyFactory factory = new ProxyFactory();
        // 2.使用工厂对象方法获取代理对象
        SellTickets proxyObject = factory.getProxyObject();
        // 调用卖电脑的方法
        proxyObject.sell();
        System.out.println(proxyObject.getClass());
        // 让程序一直执行
        while (true) {

        }
    }
}
