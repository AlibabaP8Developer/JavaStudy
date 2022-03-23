package com.xiaomi;

import java.util.*;

/**
 * 局部变量var类型推断
 */
public class Demo01 {
    public static void main(String[] args) {
        var name = "xiaomi";
        var names = new ArrayList<String>();
        var map = new HashMap<String, Integer>();
        map.put("apple", 10);
        map.put("google", 10);
        // 遍历map集合：键值对的方式
        var entrySet = map.entrySet();
        for (var entry: entrySet) {
            var key = entry.getKey();
            var value = entry.getValue();
            System.out.println(key + ":" + value);
        }
    }
}
