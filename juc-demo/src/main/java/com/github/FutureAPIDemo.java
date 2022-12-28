package com.github;

import java.util.concurrent.*;

/**
 * Future
 *    缺点：get方法求结果，如果计算没有完成容易导致程序阻塞
 */
public class FutureAPIDemo {
    public static void main(String[] args) {

        FutureTask<String> futureTask1 = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName()+"\t...come in");
            TimeUnit.SECONDS.sleep(5);
            return "task1 over";
        });

        Thread t1 = new Thread(futureTask1, "t1");
        t1.start();

        System.out.println(Thread.currentThread().getName()+"\t...end");

        try {
            // 只要调用get，得到结果才会离开，容易程序堵塞
            // 一般建议放在最后
            // 假如不想等时间长，过时不候，自动离开
            //System.out.println(futureTask1.get());
            //System.out.println(futureTask1.get(3, TimeUnit.SECONDS));
            // isDone轮询：
            //  1.轮询的方法会耗费CPU资源，而且也不见得能及时地处理得到计算结果
            //  2.如果想要异步获取结果，通常都会以轮询的方式去获取结果，尽量不要阻塞
            // future对于结果的获取不是很友好，只能通过阻塞或轮询的方式得到任务的结果
            while (true) {
                if (futureTask1.isDone()) {
                    System.out.println(futureTask1.get());
                    break;
                } else {
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.println("正在处理中...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
