package com.itheima.principles.demo1;

/**
 * 开闭原则
 */
public class Client {
    public static void main(String[] args) {
        SougouInput sougouInput = new SougouInput();

        DefaultSkin defaultSkin = new DefaultSkin();

        // 将皮肤设置到输入法中
        sougouInput.setSkin(defaultSkin);
        sougouInput.display();
    }
}
