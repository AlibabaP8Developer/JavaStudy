package com.itheima.proxy.staticProxy;

public class Client {
    public static void main(String[] args) {
        // 创建代售点对象
        ProxyPoint proxyPoint = new ProxyPoint();
        proxyPoint.sell();
    }
}
