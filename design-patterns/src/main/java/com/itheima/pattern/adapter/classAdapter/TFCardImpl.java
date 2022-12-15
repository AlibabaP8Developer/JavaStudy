package com.itheima.pattern.adapter.classAdapter;

/**
 * 适配者类
 */
public class TFCardImpl implements TFCard {

    @Override
    public String readTf() {
        String msg = "TFCard读取数据： hello TFCard";
        return msg;
    }

    @Override
    public void writeTf(String msg) {
        System.out.println("TFCard 写数据：" + msg);
    }
}
