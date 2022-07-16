package com.github.ui;

import javax.swing.*;
import java.awt.*;

/**
 * 登陆窗体
 */
public class LoginJFame extends JFrame {
    public LoginJFame() throws HeadlessException {
        // 在创建登陆界面时，同时给这个界面设置信息
        // 宽高等
        this.setSize(488, 430);
        this.setTitle("欢迎登陆！");
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
