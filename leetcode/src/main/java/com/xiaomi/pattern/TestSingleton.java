package com.xiaomi.pattern;


import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TestSingleton {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        Singleton4.otherMethod();
        System.out.println("==============");
        System.out.println(Singleton4.getInstance());
        System.out.println(Singleton4.getInstance());
        // 单例类 implements Serializable，可能破坏单例
        // 反射破坏单例
//        reflection(Singleton1.class);
        // 反序列化破坏单例
//        serializable(Singleton1.getInstance());

        // unsafe破坏单例，暂无预防手段
//        unsafe(Singleton1.class);
    }

    private static void unsafe(Class<Singleton1> singleton1Class) throws InstantiationException {
//        Object o = UnsafeUtils.getUnsafe().allocateInstance(singleton1Class);
//        System.out.println("Unsafe创建实例："+ o);
    }

    private static void serializable(Singleton1 instance) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
        objectOutputStream.writeObject(instance);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(stream.toByteArray()));
        Object o = objectInputStream.readObject();
        System.out.println("反序列化创建实例：" + o);
    }

    private static void reflection(Class<Singleton1> singleton1Class) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Constructor<?> constructor : singleton1Class.getDeclaredConstructors()) {
            System.out.println("constructor:" + constructor);
        }
        Constructor<?> constructors = singleton1Class.getDeclaredConstructor();
        constructors.setAccessible(true);
        System.out.println("反射创建实例：" + constructors.newInstance());
    }


}
