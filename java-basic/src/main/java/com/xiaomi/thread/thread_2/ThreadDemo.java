package com.xiaomi.thread.thread_2;

public class ThreadDemo {
    public static void main(String[] args) {
        /*
            多线程第一种方式
            1.自己定义一个类实现Runnable
            2.重写run方法
            创建自己类的对象
            3.创建一个thread类的对象，启动线程
         */
        MyRun myRun = new MyRun();

        Thread thread1 = new Thread(myRun);
        Thread thread2 = new Thread(myRun);
        thread1.setName("thread1");
        thread2.setName("thread2");
        thread1.start();
        thread2.start();
    }
}
