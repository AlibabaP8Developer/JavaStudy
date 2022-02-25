package com.xiaomi.pattern;

import java.io.Serializable;

/**
 * 单例模式
 *    懒汉式, 内部类
 */
public class Singleton5 implements Serializable {
    /**
     * 1.构造私有
     */
    private Singleton5() {
        System.out.println("private Singleton5");
    }

    private static class Holder {
        /**`
         * 2.提供一个静态的实例对象，创建唯一的实例对象
         * 必须加volatile修饰，可见性，有序性问题
         */
        public static volatile Singleton5 INSTANCE = new Singleton5();
    }

    /**
     * 3.公共的静态方法，返回静态成员变量
     * 加锁防止多线程破坏单例
     * 多线程 效率高
     * @return
     */
    public static Singleton5 getInstance() {
        return Holder.INSTANCE;
    }

    public static void otherMethod() {
        System.out.println("otherMethod()");
    }

}
