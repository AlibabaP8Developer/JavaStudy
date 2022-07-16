package com.gtihub;

import javax.swing.*;

public class Test2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(603, 680);
        frame.setTitle("事件演示");
        // 设置界面置顶
        frame.setAlwaysOnTop(true);
        // 设置界面居中
        frame.setLocationRelativeTo(null);
        // 设置关闭模式
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 取消默认居中位置，只有取消了才会按照xy的形式添加组件
        frame.setLayout(null);

        // 创建一个按钮
        JButton button = new JButton("点我");
        button.setBounds(0,0, 100, 50);
        // 添加动作监听
        // button组件对象，表示给哪个组件添加对象
        // addActionListener：表示要给组件添加哪个事件监听（动作监听：鼠标左键和空格）
        // 参数：表示事件被触发之后要执行的代码
        button.addActionListener((actionEvent)->{
            System.out.println("点我～～～");
        });

        // 按钮添加到指定界面中
        frame.getContentPane().add(button);

        frame.setVisible(true);
    }
}
