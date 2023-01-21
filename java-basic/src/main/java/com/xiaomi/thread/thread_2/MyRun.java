package com.xiaomi.thread.thread_2;

public class MyRun implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("这个线程："+Thread.currentThread().getName());
        }
    }
}
