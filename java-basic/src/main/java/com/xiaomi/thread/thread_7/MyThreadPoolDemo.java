package com.xiaomi.thread.thread_7;

import com.xiaomi.thread.thread_6.MyRunnable;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        /*
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor();
            （核心线程数量，最大线程数量，空闲线程最大存活时间，任务队列，创建线程工厂，任务的拒绝策略）
            参数1：核心线程数量    不能小于0
            参数2：最大线程数量    不能小于0，最大数量>=核心线程数量
            参数3：空闲线程最大存活时间  不能小于0
            参数4：时间单位       用TimeUnit指定
            参数5：任务队列       不能为null
            参数6：创建线程工厂    不能为null
            参数7：任务的拒绝策略  不能为null
         */
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                3,  // 核心线程数量，能小于0
                6, // 最大线程数量，不能小于0，最大数量>=核心线程数量
                60, // 空闲线程最大存活时间
                TimeUnit.SECONDS, //时间单位
                new ArrayBlockingQueue<>(3), // 任务队列
                Executors.defaultThreadFactory(), // 创建线程工厂
                new ThreadPoolExecutor.AbortPolicy() // 任务的拒绝策略
        );

        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.submit(new MyRunnable());
        pool.shutdown();
    }
}
