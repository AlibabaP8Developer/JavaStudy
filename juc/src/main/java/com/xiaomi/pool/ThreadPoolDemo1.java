package com.xiaomi.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 演示线程池三种常用分类
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        /*
            public static ExecutorService newCachedThreadPool() {
                return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                              60L, TimeUnit.SECONDS,
                                              new SynchronousQueue<Runnable>());}

            public ThreadPoolExecutor(
                              int corePoolSize, 常驻（核心）线程数量
                              int maximumPoolSize, 最大线程数量
                              long keepAliveTime, 线程存活时间（值）
                              TimeUnit unit, 线程存活时间（单位）
                              BlockingQueue<Runnable> workQueue, 阻塞队列
                              ThreadFactory threadFactory, 线程工厂
                              RejectedExecutionHandler handler 拒绝策略
                              ) {
                this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                     Executors.defaultThreadFactory(), defaultHandler);
            }
         */
        // 一池5线程（相当于5个窗口）
        //ExecutorService threadPool1 = Executors.newFixedThreadPool(5);

        // 一池一线程
//        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();

        // 一池可扩容线程
        ExecutorService threadPool1 = Executors.newCachedThreadPool();

        // 10个顾客请求
        try {
            for (int i = 0; i < 20; i++) {
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
