package com.github;

import java.util.concurrent.*;

/**
 * Future
 *    优点：Future线程池异步多线程任务配合，能显著提高程序的执行效率
 */
public class FutureThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 三个任务，目前只有一个线程main处理，耗时多少？

        // 三个任务，目前开启多个异步任务线程处理，耗时多少？

        ExecutorService pool = Executors.newFixedThreadPool(3);

        long start = System.currentTimeMillis();

        FutureTask<String> futureTask1 = new FutureTask<String>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return "task1 over";
        });

        FutureTask<String> futureTask2 = new FutureTask<String>(() -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "task2 over";
        });

        // new就会开启一个线程，进行一次垃圾回收，不推荐new线程
        //Thread t1 = new Thread(futureTask, "t1");
        //t1.start();

        // 使用线程池
        pool.submit(futureTask1);
        pool.submit(futureTask2);

        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());

        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("---time:" + (end - start) + "ms");

        System.out.println(Thread.currentThread().getName()+"\t---end");
        // 释放线程池资源
        pool.shutdown();
    }
}
