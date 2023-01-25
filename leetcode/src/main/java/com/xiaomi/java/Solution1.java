package com.xiaomi.java;

/**
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 *
 * 示例 1：
 * 输入: s = "leetcode"
 * 输出: false
 *
 * 示例 2：
 * 输入: s = "abc"
 * 输出: true
 */
public class Solution1 {
    public static void main(String[] args) {
        Solution1.isUnique("leetcode");
    }

    public static boolean isUnique(String astr) {
        long count = astr.chars().distinct().count();
        System.out.println(count);
        return true;
    }
}
