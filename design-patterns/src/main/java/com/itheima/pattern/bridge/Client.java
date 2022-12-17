package com.itheima.pattern.bridge;

public class Client {
    public static void main(String[] args) {
        // 创建mac系统对象
        OpratingSystem system = new Mac(new AviFile());
        // 使用操作系统播视频文件
        system.play("战狼3");
    }
}
