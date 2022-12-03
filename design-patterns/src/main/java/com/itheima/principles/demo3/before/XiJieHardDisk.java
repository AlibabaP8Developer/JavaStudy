package com.itheima.principles.demo3.before;

public class XiJieHardDisk {
    // 存储数据的方法
    public void save(String data) {
        System.out.println("使用希捷硬盘存储数据为：" + data);
    }
    // 获取数据的方法
    public String get() {
        System.out.println("使用希捷硬盘读取数据");
        return "使用希捷硬盘读取数据";
    }
}
