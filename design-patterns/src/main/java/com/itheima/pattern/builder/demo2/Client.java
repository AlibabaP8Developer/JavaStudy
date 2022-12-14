package com.itheima.pattern.builder.demo2;

public class Client {

    public static void main(String[] args) {
        // 创建手机对象，通过构建者对象获取手机对象
        Phone phone = new Phone.Builder()
                .cpu("Intel")
                .screen("三星")
                .mainBoard("华硕")
                .memory("金士顿")
                .builder();
        System.out.println(phone);
    }
}
