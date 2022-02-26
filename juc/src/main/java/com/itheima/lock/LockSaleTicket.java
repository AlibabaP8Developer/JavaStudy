package com.itheima.lock;

import com.itheima.sync.SaleTicket;

import java.util.concurrent.locks.ReentrantLock;

public class LockSaleTicket {

    /**
     * 第一步 创建资源类，定义属性和操作方法
     */
    static class Ticket {
        // 票数
        private static int number = 30;

        // 创建可重入锁
        private static final ReentrantLock lock = new ReentrantLock();

        // 操作方法 卖票
        public static void sale() {
            // 上锁
            lock.lock();
            try {
                // 判断：是否有票
                if (number > 0) {
                    System.out.println(Thread.currentThread().getName() + "：卖出," + number-- + ",剩下:" + number);
                }
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 第二步 创建多个线程，调用资源类的操作方法
     * @param args
     */
    public static void main(String[] args) {
        // 创建三个线程
        new Thread(()->{
            // 调用卖票方法
            for (int i = 0; i < 40; i++) {
                LockSaleTicket.Ticket.sale();
            }
        }, "aa").start();

        new Thread(()->{
            // 调用卖票方法
            for (int i = 0; i < 40; i++) {
                LockSaleTicket.Ticket.sale();
            }
        }, "bb").start();

        new Thread(()->{
            // 调用卖票方法
            for (int i = 0; i < 40; i++) {
                LockSaleTicket.Ticket.sale();
            }
        }, "cc").start();
    }
}
