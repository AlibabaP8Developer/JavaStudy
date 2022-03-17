package com.xiaomi.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 自定义线程池
public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        /*
            int corePoolSize, 常驻（核心）线程数量
            int maximumPoolSize, 最大线程数量
            long keepAliveTime, 线程存活时间（值）
            TimeUnit unit, 线程存活时间（单位）
            BlockingQueue<Runnable> workQueue, 阻塞队列
            ThreadFactory threadFactory, 线程工厂
            RejectedExecutionHandler handler 拒绝策略
         */
        ThreadPoolExecutor threadPool1 = new ThreadPoolExecutor(2, 5,
                2L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        // 10个顾客请求
        try {
            for (int i = 1; i <= 10; i++) {
                // 执行
                threadPool1.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "  办理业务...");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool1.shutdown();
        }
    }
}
