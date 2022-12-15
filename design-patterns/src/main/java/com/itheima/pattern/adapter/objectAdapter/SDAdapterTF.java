package com.itheima.pattern.adapter.objectAdapter;

/**
 * 适配器类
 */
public class SDAdapterTF implements SDCard {

    // 声明适配者类
    private TFCard tfCard;

    public SDAdapterTF(TFCard tfCard) {
        this.tfCard = tfCard;
    }

    @Override
    public String readSD() {
        System.out.println("adapter read tf card");
        return tfCard.readTf();
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("adpter write tf card");
        tfCard.writeTf(msg);
    }
}
