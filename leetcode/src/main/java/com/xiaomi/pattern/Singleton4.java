package com.xiaomi.pattern;

import java.io.Serializable;

/**
 * 单例模式
 *    懒汉式，DCL 双检锁
 */
public class Singleton4 implements Serializable {
    /**
     * 1.构造私有
     */
    private Singleton4() {
        System.out.println("private Singleton4");
    }

    /**`
     * 2.提供一个静态的实例对象，创建唯一的实例对象
     * 必须加volatile修饰，可见性，有序性问题
     */
    public static volatile Singleton4 INSTANCE = null;

    /**
     * 3.公共的静态方法，返回静态成员变量
     * 加锁防止多线程破坏单例
     * 多线程 效率高
     * @return
     */
    public static Singleton4 getInstance() {
        if (INSTANCE == null) {
            synchronized(Singleton4.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton4();
                }
            }
        }
        return INSTANCE;
    }

    public static void otherMethod() {
        System.out.println("otherMethod()");
    }

}
