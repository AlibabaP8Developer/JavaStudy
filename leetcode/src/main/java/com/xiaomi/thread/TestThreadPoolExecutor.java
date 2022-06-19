package com.xiaomi.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestThreadPoolExecutor {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);

        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2);

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                3,
                0,
                TimeUnit.MICROSECONDS,
                queue,
                r -> new Thread(r, "myThread" + atomicInteger.getAndIncrement()),
                new ThreadPoolExecutor.AbortPolicy()
        );

        showState(queue, threadPool);

        showState(queue, threadPool);
    }

    private static void showState(ArrayBlockingQueue<Runnable> queue, ThreadPoolExecutor threadPool) {

    }
}
