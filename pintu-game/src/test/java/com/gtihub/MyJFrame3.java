package com.gtihub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyJFrame3 extends JFrame implements KeyListener {

    JButton button1 = new JButton("点我");

    public MyJFrame3() throws HeadlessException {
        // 设置界面的宽高
        this.setSize(603, 680);
        this.setTitle("拼图单机版 v1.0");
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 取消默认的居中方式
        this.setLayout(null);

        // 整个窗体添加键盘监听
        // 调用者this，本类对象，当前的界面对象，表示要给整个界面添加监听
        // addKeyListener：表示要给本界面添加键盘监听
        // 参数this：当事件被触发之后，会执行本类中的对应代码
        this.addKeyListener(this);


        // 显示界面
        this.setVisible(true);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * 细节1：如果按下一个按键没有松开，那么会重复的去调用keyPressed方法
     * 细节2：键盘里面那么多按键，如何区分
     *   每一个按键都有一个编号对应
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("按下不松");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("松开按键");
        int keyCode = e.getKeyCode();
        System.out.println(keyCode);
        if (keyCode == 65 ) {
            System.out.println("按键 a");
        } else if (keyCode == 66) {
            System.out.println("按键 b");
        }
    }
}
