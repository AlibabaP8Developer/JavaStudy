package com.github;

import java.util.concurrent.TimeUnit;

public class DaemonDemo {

    /*
        用户线程（默认）：是系统的工作线程，会完成这个程序需要完成的业务操作
        守护线程：是一种特殊的线程为其他线程服务的，在后台默默地完成一些系统性的服务，如：垃圾回收线程
        用户线程结束，守护线程也会结束
     */
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().isDaemon());
        Thread thread1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"     start run...");
            System.out.println(Thread.currentThread().isDaemon()?"守护线程": "用户线程");
            while (true) {}
        }, "t1");
        thread1.setDaemon(true);
        thread1.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"\tend  主线程");
    }
}
