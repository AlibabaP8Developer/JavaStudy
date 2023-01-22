package com.xiaomi.thread.thread_6;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + "    这个线程：  " + Thread.currentThread().getName());
        }
    }
}
