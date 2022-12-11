package com.itheima.pattern.singleton.demo4;

/**
 * 懒汉式：双重检查锁方式
 */
public class Singleton {

    // 私有构造方法
    private Singleton() {
    }

    // 2.在本类中创建本类对象
    private static volatile Singleton instance;

    // 3.提供对外的访问方式，让外界获取该对象
    public static Singleton getInstance() {
        // 第一次判断，如果instance的值不为null，不需要抢占锁，直接返回对象
        if (instance == null) {
            synchronized (Singleton.class) {
                // 第二次判断
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
