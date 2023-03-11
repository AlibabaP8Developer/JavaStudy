package com.itheima.interview;

/**
 * 遍历前、中、后序遍历
 * <p>
 * 您的 api 密钥已成功生成，请一定保留它，因为它只会显示一次，
 * 不会存储在 sms-activate 服务器上。恢复它是不可能的，只能创建一个新的。
 * c04b6A77d200125c013598137d1f472b
 */
public class TreeTraversal {
    public static void main(String[] args) {
        /*
                1
               / \
              2   3
             /   / \
            4   5   6
         */
        TreeNode root = new TreeNode(  // 第一层
                new TreeNode(
                        new TreeNode(4), 2, null // 第三层
                ), // 左孩子 第二层
                1, // 根节点
                new TreeNode(
                        new TreeNode(5), 3, new TreeNode(6) // 第三层
                ) // 右孩子 第二层
        );
        preOrder(root);// 1	 2	 4	 3	 5	 6
        System.out.println();
        System.out.println("========");
        inOrder(root);// 4	 2	 1 	 5	 3   6
        System.out.println();
        System.out.println("========");
        postOrder(root);// 4	2	5	6	3	1
        System.out.println();
        System.out.println("========");

        LinkedListStack<TreeNode> stack = new LinkedListStack<>();

        // 当前节点
        TreeNode curr = root;
        /*while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                System.out.println("go:" + curr.val);
                // 压入栈，为了记住回来的路径
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();
                System.out.println("come:" + pop.val);
                curr = pop.right;
            }
        }*/

        System.out.println("！=========");
        TreeNode pop = null;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                System.out.println("before:" + curr.val);
                // 压入栈，为了记住回来的路径
                stack.push(curr);
                curr = curr.left;
            } else {
                // 栈顶元素
                TreeNode peek = stack.peek();
                // 没有右子树
                if (peek.right == null) {
                    System.out.println("middle:" + peek.val);
                    pop = stack.pop();
                    System.out.println("after:" + pop.val);
                }
                // 右子树处理完成
                else if (peek.right == pop) {
                    pop = stack.pop();
                    System.out.println("after:" + pop.val);
                }
                // 待处理右子树
                else {
                    System.out.println("middle:" + peek.val);
                    curr = peek.right;
                }
            }
        }
    }

    /**
     * 前序遍历 递归方式
     *
     * @param node
     */
    public static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        // 打印当前节点值
        System.out.print(node.val + "\t");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历 递归方式
     *
     * @param node
     */
    public static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        // 打印当前节点值
        inOrder(node.left);
        System.out.print(node.val + "\t");
        inOrder(node.right);
    }

    /**
     * 后序遍历 递归方式
     *
     * @param node
     */
    public static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        // 打印当前节点值
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + "\t");
    }
}
