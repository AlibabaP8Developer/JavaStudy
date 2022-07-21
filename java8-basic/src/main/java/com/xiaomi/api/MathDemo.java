package com.xiaomi.api;

public class MathDemo {
    /*
        int abs(int a): 获取参数绝对值
        double ceil(double a): 向上取整
        double floor(double a): 向下取整
        int round(float a): 四舍五入
        int max(int a, int b): 获取两个int值中的较大值
        double pow(double a, double b): 返回a的b次幂的值
        double random(): 返回值为double的随机值，范围[0.0, 1.0]
     */
    public static void main(String[] args) {
        // int类型，取值范围：-2147483648～2147483647
        System.out.println("获取参数绝对值: "+Math.abs(-2147483648));
        // System.out.println(Math.absExact()); jdk15新特性
        System.out.println("向上取整: "+Math.ceil(9.2));
        System.out.println("向下取整: "+Math.floor(9.8));
        System.out.println("四舍五入: "+Math.round(9.6));
        System.out.println("获取两个int值中的较大值: "+Math.max(9, 16));
        System.out.println("返回a的b次幂的值: "+Math.pow(2.0, 3.0));
        System.out.println("返回值为double的随机值，范围[0.0, 1.0]: "+Math.random()*99);
    }
}
