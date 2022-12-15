package com.itheima.pattern.adapter.classAdapter;

/**
 * 适配器类
 */
public class SDAdapterTF extends TFCardImpl implements SDCard {

    @Override
    public String readSD() {
        System.out.println("adapter read tf card");
        return readTf();
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("adpter write tf card");
        writeTf(msg);
    }
}
