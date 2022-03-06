package com.xiaomi.sync;

/**
 * 第一步：创建资源类，定义属性和操作方法
 */
class Share {
    // 初始值
    private int number = 0;

    // +1的方法
    public synchronized void incr() throws InterruptedException {
        // 第二步：判断  干活  通知
        // 判断 number值是否是0，如果不是0，等待
        while(number != 0) {
            this.wait();
        }
        // 如果number值是0，+1操作
        number++;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        // 通知其他线程
        this.notifyAll();
    }

    // -1的方法
    public synchronized void decr() throws InterruptedException {
        // 第二步：判断  干活  通知
        // 判断 number值是否是0，如果不是0，等待
        while (number != 1) {
            this.wait();
        }
        // 如果number值是0，+1操作
        number--;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        // 通知其他线程
        this.notifyAll();
    }
}

public class ThreadDemo1 {
    public static void main(String[] args) {
        // 创建多个线程，调用资源类的操作方法
        Share share = new Share();
        // 创建两个线程
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "aa").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "bb").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "cc").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "dd").start();
    }
}
