package com.itheima.interview;

/**
 * 金额转换
 */
public class StringDemo3 {

    public static void main(String[] args) {
        int money = 289;
        String moneyStr = "";
        while (true) {
            // 从右往左获取数据
            int ge = money % 10;
            String capitalNumber = getCapitalNumber(ge);
            moneyStr = capitalNumber + moneyStr;
            // 去掉刚刚获取的数据
            money = money / 10;
            if (money == 0) {
                break;
            }
        }
        // 在前面补0
        int count = 7 - moneyStr.length();
        for (int i = 0; i < count; i++) {
            moneyStr = "零" + moneyStr;
        }
        System.out.println(moneyStr);
    }

    public static String getCapitalNumber(int number) {
        String[] arr = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        // 得到number每个数字
        return arr[number];
    }
}
