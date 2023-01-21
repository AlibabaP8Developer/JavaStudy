package com.xiaomi.thread.thread_4;

public class ThreadDemo {
    public static void main(String[] args) {
        /*
            多线程第一种方式
            1.自己定义一个类继承thread类
            2.重新run方法
            3.创建子类对象，启动线程
         */
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        MyThread thread3 = new MyThread();
        MyThread thread4 = new MyThread();
        thread1.setName("完颜雍");
        thread2.setName("完颜亮");
        thread3.setName("完颜宗弼");
        thread4.setName("赵光义");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
