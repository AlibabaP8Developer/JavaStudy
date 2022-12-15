package com.itheima.pattern.adapter.objectAdapter;

/**
 * 计算机类
 */
public class Computer {

    // 从SD卡中读取数据
    public String readSD(SDCard sdCard) {
        if (sdCard == null) {
            throw new NullPointerException("sd card is not null");
        }
        return sdCard.readSD();
    }
}
