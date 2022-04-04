package com.xiaomi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 局部变量var类型推断
 */
public class Demo02 {
    public static void main(String[] args) {
        var str = "      itcast  -  atguigu  -  alibaba - tencent    ";
        // jdk11  str.strip()可以去除全角空格
        System.out.println("去除字符串的前后空白："+ str.strip());
        System.out.println("去除字符串的前后空格："+ str.trim());
    }
}
