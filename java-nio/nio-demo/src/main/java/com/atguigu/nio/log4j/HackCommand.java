package com.atguigu.nio.log4j;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class HackCommand {
    public HackCommand() throws Exception {
        System.out.println("执行攻击指令！");
        Runtime runtime = Runtime.getRuntime();
        String property = System.getProperty("os.name");
        for (int i = 0; i < 1; i++) {
            if ("Mac OS X".equals(property)) {
                String[] commands = {"/bin/sh", "open /System/Applications/QQ.app"};
                runtime.exec(commands);
            } else {
                runtime.exec("cmd /k notepad");
                Thread.sleep(1000);
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_Q);
                robot.keyPress(KeyEvent.VK_I);

                robot.keyPress(KeyEvent.VK_W);
                robot.keyPress(KeyEvent.VK_O);

                robot.keyPress(KeyEvent.VK_X);
                robot.keyPress(KeyEvent.VK_I);

                robot.keyPress(KeyEvent.VK_H);
                robot.keyPress(KeyEvent.VK_U);
                robot.keyPress(KeyEvent.VK_A);
                robot.keyPress(KeyEvent.VK_N);

                robot.keyPress(KeyEvent.VK_N);
                robot.keyPress(KeyEvent.VK_I);

                robot.keyPress(KeyEvent.VK_H);
                robot.keyPress(KeyEvent.VK_E);
                robot.keyPress(KeyEvent.VK_N);

                robot.keyPress(KeyEvent.VK_J);
                robot.keyPress(KeyEvent.VK_I);
                robot.keyPress(KeyEvent.VK_U);

                robot.keyPress(KeyEvent.VK_ENTER);

                robot.keyPress(KeyEvent.VK_A);
                robot.keyPress(KeyEvent.VK_I);
                robot.keyPress(KeyEvent.VK_N);
                robot.keyPress(KeyEvent.VK_I);
                robot.keyPress(KeyEvent.VK_O);

                robot.keyPress(KeyEvent.VK_ENTER);

            }
        }
    }

    public static void main(String[] args) {
        String property = System.getProperty("os.name");
        System.out.println(property);
    }
}
