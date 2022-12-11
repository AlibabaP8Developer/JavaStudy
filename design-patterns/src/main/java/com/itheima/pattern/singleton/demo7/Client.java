package com.itheima.pattern.singleton.demo7;

import java.io.*;

/**
 * 测试使用反射破坏单例模式
 */
public class Client {

    public static void main(String[] args) {
        try {
//            writeObject2File();
            readObjectFromFile();
            readObjectFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 向文件中写数据（对象）
    public static void readObjectFromFile() throws Exception {
        // 创建对象输入流对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/lizhenghang/Desktop/hello.txt"));
        // 读取对象
        Singleton instance = (Singleton) ois.readObject();
        System.out.println(instance);
        ois.close();
    }

    // 从文件中写数据（对象）
    public static void writeObject2File() throws IOException {
        // 1.获取Singleton对象
        Singleton instance = Singleton.getInstance();
        // 2.创建对象输出流对象
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/lizhenghang/Desktop/hello.txt"));
        oos.writeObject(instance);
        // 释放资源
        oos.close();
    }

}
