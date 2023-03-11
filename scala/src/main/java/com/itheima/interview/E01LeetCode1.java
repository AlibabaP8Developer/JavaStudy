package com.itheima.interview;

import java.util.ArrayList;
import java.util.List;

public class E01LeetCode1 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(  // 第一层
                new TreeNode(
                        new TreeNode(4), 2, null // 第三层
                ), // 左孩子 第二层
                1, // 根节点
                new TreeNode(
                        new TreeNode(5), 3, new TreeNode(6) // 第三层
                ) // 右孩子 第二层
        );
        List<Integer> list = preorderTraversal(root);
        System.out.println(list);
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        LinkedListStack<TreeNode> stack = new LinkedListStack<>();
        TreeNode curr = root;
        TreeNode pop = null;
        List<Integer> result = new ArrayList<>();

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                // 待处理的左子树
                result.add(curr.val); // 前序
                // 压入栈，为了记住回来的路径
                stack.push(curr);
                curr = curr.left;
            } else {
                // 栈顶元素
                TreeNode peek = stack.peek();
                // 没有右子树
                if (peek.right == null) {
                    pop = stack.pop();
                }
                // 右子树处理完成
                else if (peek.right == pop) {
                    pop = stack.pop();
                }
                // 待处理右子树
                else {
                    curr = peek.right;
                }
            }
        }

        return result;
    }
}
