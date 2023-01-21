package com.xiaomi.thread.thread_5;

import java.util.ArrayList;
import java.util.Collections;

public class ThreadDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 10,5,50,100,200,500,800,2,80);
        MyThread myThread1 = new MyThread(list);
        MyThread myThread2 = new MyThread(list);
        MyThread myThread3 = new MyThread(list);

        myThread1.setName("抽奖1");
        myThread2.setName("抽奖2");
        myThread3.setName("抽奖3");

        myThread1.start();
        myThread2.start();
        myThread3.start();
    }
}
