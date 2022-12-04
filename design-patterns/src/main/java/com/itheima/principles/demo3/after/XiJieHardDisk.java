package com.itheima.principles.demo3.after;

public class XiJieHardDisk implements HardDisk {
    // 存储数据的方法
    @Override
    public void save(String data) {
        System.out.println("使用希捷硬盘存储数据为：" + data);
    }
    // 获取数据的方法
    @Override
    public String get() {
        System.out.println("使用希捷硬盘读取数据");
        return "使用希捷硬盘读取数据";
    }
}
