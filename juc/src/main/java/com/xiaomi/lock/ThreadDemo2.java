package com.xiaomi.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 第一步：创建资源类，定义属性和操作方法
 */
class Share {
    /**
     * 初始值
     */
    private int number = 0;

    /**
     * 创建lock
     */
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * +1的方法
     *
     * @throws InterruptedException
     */
    public void incr() throws InterruptedException {
        // 上锁
        lock.lock();

        try {
            // 判断
            while (number != 0) {
                condition.await();
            }
            // 干活
            number++;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            // 通知
            condition.signalAll();
        } finally {
            // 解锁
            lock.unlock();
        }
    }

    /**
     * -1的方法
     *
     * @throws InterruptedException
     */
    public void decr() throws InterruptedException {
        // 上锁
        lock.lock();

        try {
            // 第二步：判断  干活  通知
            // 判断 number值是否是0，如果不是0，等待
            while (number != 1) {
                condition.wait();
            }
            // 如果number值是0，+1操作
            number--;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            // 通知其他线程
            condition.signalAll();
        } finally {
            // 解锁
            lock.unlock();
        }
    }
}

public class ThreadDemo2 {
    public static void main(String[] args) {
        // 创建多个线程，调用资源类的操作方法
        Share share = new Share();
        // 创建两个线程
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "aa").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "bb").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "cc").start();

        new Thread(() -> {
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
