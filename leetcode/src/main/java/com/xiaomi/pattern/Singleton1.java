package com.xiaomi.pattern;

import java.io.Serializable;

/**
 * 单例模式
 * 1.掌握单例模式常见五种实现方式
 *  (1) 饿汉式
 * 2.了解jdk中有哪些地方体现了单例模式
 *
 */
public class Singleton1 implements Serializable {
    /**
     * 1.构造私有
     */
    private Singleton1() {
        // 防止反射破坏单例
        if (INSTANCE != null) {
            throw  new RuntimeException("单例对象不能重复创建！");
        }
        System.out.println("private Singleton1");
    }

    /**
     * 2.提供一个静态的实例对象，创建唯一的实例对象
     */
    public static final Singleton1 INSTANCE = new Singleton1();

    /**
     * 3.公共的静态方法，返回静态成员变量
     * @return
     */
    public static Singleton1 getInstance() {
        return INSTANCE;
    }

    public static void otherMethod() {
        System.out.println("otherMethod()");
    }

    /**
     * 防止反序列化破坏单例
     * @return
     */
    public Object readResolve() {
        return INSTANCE;
    }

}
