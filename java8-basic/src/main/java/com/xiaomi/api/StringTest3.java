package com.xiaomi.api;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.StringJoiner;

public class StringTest3 {

    /**
     * 阿拉伯数字转汉字大写数字
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个金额：");
        int money = sc.nextInt();
        while (true) {
            if (money >= 0 && money <= 9999999) {
                break;
            } else {
                System.out.println("金额无效！");
            }
        }

        StringJoiner moneyStr = new StringJoiner(",");

        // 得到money里面的每一位数字
        while (true) {
            // 从右往左获取数据，因为右侧是数据的个位
            int ge = money % 10;
            String capitalNumber = getCapitalNumber(ge);

            moneyStr.add(capitalNumber);

            // 去掉刚刚获取的数据
            money = money / 10;
            // 如果数字上的每一位全部获取到了，那么money记录的就是0，此时循环结果
            if (money == 0) {
                break;
            }
        }

        System.out.println(moneyStr);
    }

    public static String getCapitalNumber(int money) {
        // 定义数组，让数字跟大写的中文产生一个对应关系
        String[] arr = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
        return arr[money];
    }
}
