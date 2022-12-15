package com.itheima.pattern.adapter.objectAdapter;

public class Client {
    public static void main(String[] args) {
        // 创建计算机对象
        Computer computer = new Computer();
        // 读取sd卡数据
        String msg = computer.readSD(new SDCardImpl());
        System.out.println(msg);

        System.out.println("==========");

        // 使用该电脑读取TF卡中的数据
        // 创建适配器类对象
        SDAdapterTF sdAdapterTF = new SDAdapterTF(new TFCardImpl());
        String msg1 = computer.readSD(sdAdapterTF);
        System.out.println(msg1);
    }
}
