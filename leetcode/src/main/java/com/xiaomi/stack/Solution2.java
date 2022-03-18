package com.xiaomi.stack;

import java.util.Arrays;

/**
 * 词典中最长的单词
 * <p>
 * 给出一个字符串数组words 组成的一本英语词典。返回words 中最长的一个单词，
 * 该单词是由words词典中其他单词逐步添加一个字母组成。
 * 若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
 * <p>
 * 示例 1：
 * 输入：words = ["w","wo","wor","worl", "world"]
 * 输出："world"
 * 解释： 单词"world"可由"w", "wo", "wor", 和 "worl"逐步添加一个字母组成。
 */
public class Solution2 {

    public String longestWord(String[] words) {
        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            } else {
                return b.compareTo(a);
            }
        });
        return "";
    }

    public static void main(String[] args) {
        String[] words = {"w", "wokkkkkk", "wor", "worl", "world"};
        String s = new Solution2().longestWord(words);
//        System.out.println(s);
    }
}
