package com.xiaomi.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {

    // 定义标志位
    // 1 aa, 2 bb, 3 cc
    private int flag = 1;
    // 创建lock锁
    private Lock lock = new ReentrantLock();
    // 创建3个condition
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    // 打印5次，参数第几轮
    public void print5(int loop) throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            while (flag != 1) {
                // 等待
                condition1.wait();
            }
            // 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + loop + "::" + i);
            }
            // 通知
            flag = 2; // 修改标志位2
            condition2.signal();// 通知bb线程
        } finally {
            lock.unlock();
        }
    }

    public void print10(int loop) throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            while (flag != 2) {
                // 等待
                condition1.wait();
            }
            // 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + loop + "::" + i);
            }
            // 通知
            flag = 3; // 修改标志位3
            condition3.signal();// 通知cc线程
        } finally {
            lock.unlock();
        }
    }

    public void print15(int loop) throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            while (flag != 3) {
                // 等待
                condition1.wait();
            }
            // 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + loop + "::" + i);
            }
            // 通知
            flag = 1; // 修改标志位1
            condition1.signal();// 通知aa线程
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadDemo3 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "aa").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "bb").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "cc").start();
    }
}
