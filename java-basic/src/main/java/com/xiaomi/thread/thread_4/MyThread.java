package com.xiaomi.thread.thread_4;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * 抢红包
 * <p>
 * 抢红包也用到了多线程
 * 假设：100块，分成了3个包，现在有5个人去抢
 * 其中，红包是共享数据
 * 5个人是5条线程
 * 打印结果如下：
 * xxx抢到了xxx元
 * xxx抢到了xxx元
 * xxx抢到了xxx元
 * xxx没抢到
 * xxx没抢到
 */
public class MyThread extends Thread {

    // 共享数据
    // 100元 分3个红包
    static BigDecimal money = BigDecimal.valueOf(100.0);
    static int count = 3;

    // 最小的中奖金额
    static final BigDecimal MIN = BigDecimal.valueOf(0.01);

    @Override
    public void run() {
        // 同步代码块
        synchronized (MyThread.class) {
            if (count == 0) {
                // 判断，共享数据是否到了末尾（已到末尾）
                System.out.println(getName() + "没抢到红包");
            } else {
                // 判断，共享数据是否到了末尾（没到末尾）
                // 定义一个变量，表示中奖的金额
                BigDecimal price;
                if (count == 1) {
                    // 表示此时是最后一个红包
                    // 无需随机，剩余所有的钱都是中奖金额
                    price = money;
                } else {
                    // 不能直接随机
                    Random random = new Random();
                    // 第一个红包：99.98 0.01 0.01
                    // 100-(3-1)*0.01
                    double bounds = money.subtract(BigDecimal.valueOf(count - 1).multiply(MIN)).doubleValue();
                    // 抽奖金额
                    price = BigDecimal.valueOf(random.nextDouble(bounds));// jdk17
                }
                // 设置抽中红包，小数点保留2位，四舍五入
                price = price.setScale(2, RoundingMode.HALF_UP);
                // 从money当中，去掉当前中奖的金额
                money = money.subtract(price);
                // 红包的个数-1
                count--;
                // 本次红包的信息进行打印
                System.out.println(getName() + "抢到了" + price + "元");
            }
        }
    }
}
