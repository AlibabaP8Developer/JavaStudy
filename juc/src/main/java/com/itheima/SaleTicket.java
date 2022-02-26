package com.itheima;

public class SaleTicket {

    /**
     * 第一步 创建资源类，定义属性和操作方法
     */
    static class Ticket {
        // 票数
        private static int number = 40;

        // 操作方法 买票
        public static synchronized void sale() {
            // 判断：是否有票
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "：卖出：" + number-- + "剩下:" + number);
            }
        }
    }

    /**
     * 第二步 创建多个线程，调用资源类的操作方法
     * @param args
     */
    public static void main(String[] args) {
        // 创建ticket对象
//        Ticket ticket = new Ticket();
        // 创建三个线程
        new Thread(()->{
            // 调用卖票方法
            for (int i = 0; i < 40; i++) {
                Ticket.sale();
            }
        }, "aa").start();

        new Thread(()->{
            // 调用卖票方法
            for (int i = 0; i < 40; i++) {
                Ticket.sale();
            }
        }, "bb").start();

        new Thread(()->{
            // 调用卖票方法
            for (int i = 0; i < 40; i++) {
                Ticket.sale();
            }
        }, "cc").start();
    }
}
