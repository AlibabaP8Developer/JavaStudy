package com.itheima.pattern.adapter.classAdapter;

/**
 * 具体的SD卡
 */
public class SDCardImpl implements SDCard {

    @Override
    public String readSD() {
        String msg = "sd card 读取数据： hello sd";
        return msg;
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("SDCard写数据 msg："+ msg);
    }
}
