package com.xiaomi.thread.thread_5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 抽奖
 */
public class MyThread extends Thread {

    // 奖池 {10,5,50,100,200,500,800,2,80}
    // 集合
    List<Integer> list;

    public MyThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        // 循环
        while (true) {
            synchronized (MyThread.class) {
                if (list.size() == 0) {
                    break;
                } else {
                    // 继续抽奖
                    Collections.shuffle(list);
                    Integer prize = list.remove(0);
                    System.out.println(getName() + " 又产生了一个" + prize + "元大奖");
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
