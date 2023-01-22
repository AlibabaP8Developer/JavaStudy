package com.xiaomi.thread.thread_5;

import java.util.Collections;
import java.util.List;

/**
 * 抽奖
 *     每次抽的过程中，不打印，抽完时一次性打印（随机）
 *     在此次抽奖过程中，抽奖箱1总共产生了6个奖项
 *         分别为：10，20，100，500，2，300最高奖项为300元，总计额为932元
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
