package com.itheima.pattern.singleton.demo3;

/**
 * 懒汉式
 */
public class Singleton {

    // 私有构造方法
    private Singleton() {}

    // 2.在本类中创建本类对象
    private static Singleton instance;

    // 3.提供对外的访问方式，让外界获取该对象
    public static synchronized Singleton getInstance() {
        // 判断instance是否为null，如果为null，说明还没创建Singleton对象
        // 如果没有，创建一个返回；如果有，直接返回
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
