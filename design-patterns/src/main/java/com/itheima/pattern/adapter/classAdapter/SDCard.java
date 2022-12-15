package com.itheima.pattern.adapter.classAdapter;

/**
 * 目标接口
 */
public interface SDCard {
    // 从SDCard读取数据
    String readSD();
    // SDCard中写数据
    void writeSD(String msg);
}
