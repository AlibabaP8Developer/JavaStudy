package com.xiaomi.offer;

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
 *
 * 限制：0 <= len(s) <= 100
 * 如果你不使用额外的数据结构，会很加分。
 */
public class Solution1 {

    public boolean isUnique(String astr) {
        long count = astr.chars().distinct().count();
        int length = astr.length();
        return count == length;
    }

    public static void main(String[] args) {
        String s = "letcod";
        boolean unique = new Solution1().isUnique(s);
        System.out.println(unique);
    }
}
