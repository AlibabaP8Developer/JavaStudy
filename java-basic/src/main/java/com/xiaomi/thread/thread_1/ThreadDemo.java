package com.xiaomi.thread.thread_1;

public class ThreadDemo {
    public static void main(String[] args) {
        /*
            多线程第一种方式
            1.自己定义一个类继承thread类
            2.重新run方法
            3.创建子类对象，启动线程
         */
        MyThread thread = new MyThread();
        thread.setName( Thread.currentThread().getName()+"1");
        MyThread thread2 = new MyThread();
        thread2.setName( Thread.currentThread().getName()+"2");
        thread.start();
        thread2.start();
    }
}
