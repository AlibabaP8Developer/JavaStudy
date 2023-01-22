package com.xiaomi.thread.thread_5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 抽奖
 * 每次抽的过程中，不打印，抽完时一次性打印（随机）
 * 在此次抽奖过程中，抽奖箱2总共产生了6个奖项
 * 分别为：5，50，200，800，80，700最高奖项为800元，总计额为1835元
 */
public class MyThread2 extends Thread {

    // 奖池 {10,5,50,100,200,500,800,2,80}
    // 集合
    List<Integer> list;

    public MyThread2(List<Integer> list) {
        this.list = list;
    }

    // 存储抽奖结果
    // 线程1
    static ArrayList<Integer> list1 = new ArrayList();
    // 线程2
    static ArrayList<Integer> list2 = new ArrayList();
    // 线程3
    static ArrayList<Integer> list3 = new ArrayList();

    @Override
    public void run() {
        // 循环
        while (true) {
            synchronized (MyThread2.class) {
                if (list.size() == 0) {
                    if ("抽奖1".equals(getName())) {
                        System.out.println("抽奖1" + list1);
                    } else if ("抽奖2".equals(getName())) {
                        System.out.println("抽奖2" + list2);
                    } else {
                        System.out.println("抽奖3" + list3);
                    }
                    break;
                } else {
                    // 继续抽奖
                    Collections.shuffle(list);
                    Integer prize = list.remove(0);
                    if ("抽奖1".equals(getName())) {
                        list1.add(prize);
                    } else if ("抽奖2".equals(getName())) {
                        list2.add(prize);
                    } else {
                        list3.add(prize);
                    }
                    //System.out.println(getName() + " 又产生了一个" + prize + "元大奖");
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
