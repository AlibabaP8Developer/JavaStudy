package com.github.lock;

import java.util.concurrent.TimeUnit;

/**
 * synchronized三种应用方式
 * 8种锁的案例实际提现在3个地方
 *      作用于实例方法
 *      作用于代码块
 *      作用于静态方法
 */
public class Lock8Demo {
    public static void main(String[] args) {
        /*
            谈谈你对多线程锁的理解
            8锁案例说明
            1.标准访问有ab两个线程，请问先打印邮件还是短信
            2.
         */
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendEmail();
        }, "a").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone.sendSMS();
        }, "b").start();
    }
}

// 资源类
class Phone {
    public static synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("...sendEmail");
    }

    public synchronized void sendSMS() {
        System.out.println("...sendSMS");
    }
}
