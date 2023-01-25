package com.xiaomi.java;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串""。
 * <p>
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * <p>
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 */
public class Solution4 {
    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        String s = longestCommonPrefix(strs);
        System.out.println(s);
    }

    /**
     * 思路：
     * 纵向扫描
     * 获取行和列
     * 行：元素
     * 列：第一个字符串的长度
     * 最长公共前缀不可能超过第一个字符串的长度
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        int rows = strs.length;
        int cols = strs[0].length();

        // 先列后行
        // flower 第一行
        // flow   第二行
        // flight 第三行
        for (int i = 0; i < cols; i++) {
            // 第一列的第一行的第一个字符
            char firstChar = strs[0].charAt(i);
            for (int j = 1; j < rows; j++) {
                if (strs[j].length() == i || strs[j].charAt(i) != firstChar) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
