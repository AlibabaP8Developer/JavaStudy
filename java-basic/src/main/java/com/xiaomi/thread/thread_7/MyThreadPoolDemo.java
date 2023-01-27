package com.xiaomi.thread.thread_7;

import com.xiaomi.thread.thread_6.MyRunnable;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        /*
            自定义线程池
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor();
            （核心线程数量，最大线程数量，空闲线程最大存活时间，任务队列，创建线程工厂，任务的拒绝策略）
            参数1：核心线程数量    不能小于0
            参数2：最大线程数量    不能小于0，最大数量>=核心线程数量
            参数3：空闲线程最大存活时间  不能小于0
            参数4：时间单位       用TimeUnit指定
            参数5：任务队列       不能为null
            参数6：创建线程工厂    不能为null
            参数7：任务的拒绝策略  不能为null

            线程池多大合适
            CPU密集型运算=最大并行数+1
            I/O密集型运算=最大并行数 * 期望CPU利用率 * (总时间（CPU计算时间+等待时间）/ CPU计算时间)

            ThreadPoolExecutor.AbortPolicy 默认策略：丢弃任务并抛出RejectedExecutionException异常
            ThreadPoolExecutor.DiscardPolicy 丢失任务，但是不抛出异常 这是不推荐的做法
            ThreadPoolExecutor.DiscardOldestPolicy 抛弃队列中等待最久的任务，然后把当前任务加入到队列中
            ThreadPoolExecutor.CallerRunsPolicy 调度任务的run()方法绕过线程池直接执行
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

        poolExecutor.submit(new MyRunnable());

        poolExecutor.shutdown();
    }
}
