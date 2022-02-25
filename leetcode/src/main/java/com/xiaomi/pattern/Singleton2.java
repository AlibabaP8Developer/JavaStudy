package com.xiaomi.pattern;

/**
 * 单例模式
 * 1.掌握单例模式常见五种实现方式
 *  (1) 枚举饿汉式
 * 2.了解jdk中有哪些地方体现了单例模式
 * @author alibaba
 */
public enum Singleton2 {
    // INSTANCE
    INSTANCE;

    /**
     * 枚举默认构造是私有的
     */
    Singleton2() {
        System.out.println("private Singleton2");
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    public static Singleton2 getInstance() {
        return INSTANCE;
    }

    /**
     * otherMethod
     */
    public static void otherMethod() {
        System.out.println("otherMethod");
    }
}
