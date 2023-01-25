package com.xiaomi.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * <p>
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 */
public class Solution3 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        boolean valid = isValid("()[]{}");
        System.out.println(valid);
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");
    }

    /**
     * 考虑3种括号
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        if (s.length() % 2 == 1) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                // 入栈
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                // 出栈
                char top = stack.pop();
                if (c == ')' && top != '(')
                    return false;
                if (c == ']' && top != '[')
                    return false;
                if (c == '}' && top != '{')
                    return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 单对括号
     *
     * @param s
     * @return
     */
    public static boolean isValid1(String s) {
        if (s.length() % 2 == 1) return false;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

}
