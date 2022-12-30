package com.github.controller;

import com.github.util.CbcUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestCBCController {

    @GetMapping("")
    public void testCbc(String sm4Key, String src, String iv) {
        //String src = "I Love You";
        //String iv = CbcUtil.get32UUID();
        //String sm4Key = CbcUtil.generateSM4Key();
        System.out.println("sm4Key:" + sm4Key);
        System.out.println("--生成SM4结束--");
        System.out.println("--SM4的CBC加密--");
        String s1 = CbcUtil.SM4EncForCBC(sm4Key, src, iv);
        System.out.println("密文:" + s1);
        String text = CbcUtil.SM4DecForCBC(sm4Key, s1, iv);
        System.out.println("CBC解密:   " + text);
    }

    public static void main(String[] args) {
        String src = "I Love You";
        String iv = CbcUtil.get32UUID();
        String sm4Key = CbcUtil.generateSM4Key();
        System.out.println("sm4Key:" + sm4Key);
        System.out.println("--生成SM4结束--");
        System.out.println("--SM4的CBC加密--");
        String s1 = CbcUtil.SM4EncForCBC(sm4Key, src, iv);
        System.out.println("密文:" + s1);
        String text = CbcUtil.SM4DecForCBC(sm4Key, s1, iv);
        System.out.println("CBC解密:   " + text);
    }
}
