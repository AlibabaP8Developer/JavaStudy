package com.itheima.pattern.singleton.demo5;

/**
 * 懒汉式：静态内部类方式
 */
public class Singleton {

    // 私有构造方法
    private Singleton() {
    }

    // 2.定义一个静态内部类
    private static class SingletonHolder {
        // 在内部类中声明并初始化外部类的对象
        private static final Singleton INSTANCE = new Singleton();
    };

    // 3.提供对外的访问方式，让外界获取该对象
    public static Singleton getInstance() {

        return SingletonHolder.INSTANCE;
    }
}
