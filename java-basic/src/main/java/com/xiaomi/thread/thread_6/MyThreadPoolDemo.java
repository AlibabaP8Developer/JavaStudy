package com.xiaomi.thread.thread_6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        /*
            public static ExecutorService newCachedThreadPool()  创建一个没有上限的线程池
            public static ExecutorService newFixedThreadPool(int nThreads)  创建有上限的线程池
         */

        // 1.创建线程池对象
    /*    ExecutorService pool = Executors.newCachedThreadPool();
        // 2.提交任务
        pool.submit(new MyRunnable());
        pool.submit(new MyRunnable());
        pool.submit(new MyRunnable());
        pool.submit(new MyRunnable());
        // 3.销毁线程池
        pool.shutdown();
*/
        ExecutorService pool2 = Executors.newFixedThreadPool(3);
        pool2.submit(new MyRunnable());
        pool2.submit(new MyRunnable());
        pool2.submit(new MyRunnable());
        pool2.submit(new MyRunnable());
        pool2.submit(new MyRunnable());
        pool2.shutdown();
    }
}
