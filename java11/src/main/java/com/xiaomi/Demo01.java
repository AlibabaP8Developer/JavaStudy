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
        // lambda表达式简化匿名内部类写法
        List<Integer> scores = new ArrayList<Integer>();
        Collections.addAll(scores, 9, 10, 2);
        // 降序排序
        scores.sort((@Deprecated var o1, @Deprecated var o2) -> {
            return o1.compareTo(o2);
        });
        System.out.println(scores);
    }
}
