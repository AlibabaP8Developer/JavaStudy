package com.xiaomi;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CaptchaTest {

    public static void main(String[] args) {
        // 中文验证码
        ChineseCaptcha chineseCaptcha = new ChineseCaptcha(150, 60);
        String text = chineseCaptcha.text();
        System.out.println(text);
        try {
            chineseCaptcha.out(new FileOutputStream(new File("/Users/lizhenghang/Desktop/test.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 算术验证码
        ArithmeticCaptcha captcha = new ArithmeticCaptcha();
        String text1 = captcha.text();
        System.out.println(text1);
        try {
            captcha.out(new FileOutputStream(new File("/Users/lizhenghang/Desktop/test1.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
