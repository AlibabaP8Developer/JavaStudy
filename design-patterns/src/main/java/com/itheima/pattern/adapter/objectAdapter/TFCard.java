package com.itheima.pattern.adapter.objectAdapter;

/**
 * 适配者类的接口
 */
public interface TFCard {
    // 从TF卡中读取数据
    String readTf();
    // 往TF中写数据
    void writeTf(String msg);
}
