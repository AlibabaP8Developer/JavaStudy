package com.xiaomi.thread.thread_3;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyCallable implements Callable<Integer> {

    Lock lock = new ReentrantLock();

    @Override
    public Integer call() throws Exception {
        lock.lock();
        // 求1-100的和
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        lock.unlock();
        return sum;
    }
}
