package com.xiaomi.thread.thread_1;

public class MyThread extends  Thread {

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            System.out.println("这个线程："+getName());
        }
    }
}
