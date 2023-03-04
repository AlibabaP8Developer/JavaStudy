package com.itheima.interview;

/**
 * 树节点类
 */
public class TreeNode {
    // 根结点
    public int val;
    // 左孩子
    public TreeNode left;
    // 右孩子
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(TreeNode left, int val, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
