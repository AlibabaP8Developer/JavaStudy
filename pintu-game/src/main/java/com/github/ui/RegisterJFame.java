package com.github.ui;

import javax.swing.*;
import java.awt.*;

/**
 * 注册窗体
 */
public class RegisterJFame extends JFrame {
    public RegisterJFame() throws HeadlessException {
        this.setSize(488, 500);
        this.setTitle("欢迎注册");
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
