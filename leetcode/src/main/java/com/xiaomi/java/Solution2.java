package com.xiaomi.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class Solution2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Solution2.letterCombinations("234");
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");
    }

    public static List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if ("".equals(digits) || digits == null) {
            return list;
        }

        char[][] arr = {
                {}, // 0
                {}, // 1
                {'a', 'b', 'c'}, // 2
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'},
        };

        //String digits="234"
        String[] split = digits.split("");
        for (String s : split) {
            int digit = Integer.parseInt(s);
            for (int i = 0; i < arr[digit].length; i++) {
                // abc 2,0  3,0
                char chars1 = arr[digit][i];
                char chars2 = arr[digit+1][i];
                String c = chars1+"" + chars2+"";
                System.out.println(c);
            }
        }

        //System.out.println(list);
        return list;
    }
}
