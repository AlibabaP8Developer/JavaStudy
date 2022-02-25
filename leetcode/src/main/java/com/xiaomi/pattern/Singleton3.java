package com.xiaomi.pattern;

import java.io.Serializable;

/**
 * 单例模式
 *  (1) 懒汉式
 */
public class Singleton3 implements Serializable {
    /**
     * 1.构造私有
     */
    private Singleton3() {
        System.out.println("private Singleton3");
    }

    /**`
     * 2.提供一个静态的实例对象，创建唯一的实例对象
     */
    public static Singleton3 INSTANCE = null;

    /**
     * 3.公共的静态方法，返回静态成员变量
     * 加锁防止多线程破坏单例
     * @return
     */
    public static synchronized Singleton3 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton3();
        }
        return INSTANCE;
    }

    public static void otherMethod() {
        System.out.println("otherMethod()");
    }

}
