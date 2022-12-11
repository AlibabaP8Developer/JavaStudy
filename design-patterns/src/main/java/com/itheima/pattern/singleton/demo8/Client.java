package com.itheima.pattern.singleton.demo8;

import java.lang.reflect.Constructor;

/**
 * 测试使用反射破坏单例模式
 */
public class Client {

    public static void main(String[] args) {
        // 1.获取Singleton的字节码对象
        Class clazz = Singleton.class;
        // 2.获取无参构造方法对象
        try {
            Constructor constructor = clazz.getDeclaredConstructor();
            // 3.取消访问检查
            constructor.setAccessible(true);
            // 4.创建Singleton对象
            Singleton singleton = (Singleton) constructor.newInstance();
            Singleton singleton1 = (Singleton) constructor.newInstance();
            System.out.println(singleton1 == singleton);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
