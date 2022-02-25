package com.xiaomi.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * boolean empty() 测试堆栈是否为空。
 * Object peek( ) 查看堆栈顶部的对象，但不从堆栈中移除它。
 * Object pop( ) 移除堆栈顶部的对象，并作为此函数的值返回该对象。
 * Object push(Object element) 把项压入堆栈顶部。
 * int search(Object element) 返回对象在堆栈中的位置，以 1 为基数。
 */
public class StackDemo {

    static void showpush(Stack<Integer> st, int a) {
        st.push(new Integer(a));
        System.out.println("push(" + a + ")");
        System.out.println("stack: " + st);
    }

    static void showpop(Stack<Integer> st) {
        System.out.print("pop -> ");
        Integer a = (Integer) st.pop();
        System.out.println(a);
        System.out.println("stack: " + st);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        showpush(stack, 41);
        showpush(stack, 42);
        showpush(stack, 44);
        showpush(stack, 43);
        System.out.println("==========");
        // 出栈
        showpop(stack);
        showpop(stack);
        showpop(stack);
        showpop(stack);
    }

}
