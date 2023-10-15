package com.itheima.interview;

/**
 * 二叉搜索树
 */
public class BSTTree1 {

    // 根节点
    BSTNode root;

    static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 直接关键字对应的值
     *
     * @param key
     * @return
     */
    public Object get(int key) {

        return null;
    }

    private Object doGet(BSTNode node, int key) {
        if (node == null) {
            // 没找到
            return null;
        }
        if (key < node.key) {
            // 向左找
            return doGet(node.left, key);
        } else if (key > node.key) {
            // 向右找
            return doGet(node.right, key);
        } else {
            // 找到了
            return node.value;
        }
    }

    /**
     * 查找最小关键字对应的值
     *
     * @return
     */
    public Object min() {
        return null;
    }

    /**
     * 存储关键字和对应值
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
    }

    /**
     * 查找关键字的前驱值
     *
     * @param key
     * @return
     */
    public Object successor(int key) {
        return null;
    }

    /**
     * 查找关键字的后继值
     *
     * @param key
     * @return
     */
    public Object predecessor(int key) {
        return null;
    }

    /**
     * 查找关键字删除
     *
     * @param key
     * @return
     */
    public Object delete(int key) {
        return null;
    }


    public BSTTree1 createTree() {
        /*
                      4
                   /     \
                 2        6
               /  \      /  \
              1    3    5     7
         */
        BSTTree1.BSTNode node1 = new BSTNode(1, "朱元璋");
        BSTTree1.BSTNode node3 = new BSTNode(3, "耶律大石");
        BSTTree1.BSTNode node2 = new BSTNode(2, "完颜兀术", node1, node3);

        BSTTree1.BSTNode node5 = new BSTNode(5, "完颜昌");
        BSTTree1.BSTNode node7 = new BSTNode(7, "完颜雍");
        BSTTree1.BSTNode node6 = new BSTNode(6, "完颜允恭", node5, node7);

        BSTTree1.BSTNode root = new BSTNode(4, "完颜璟", node2, node6);

        BSTTree1 tree = new BSTTree1();
        tree.root = root;
        return tree;
    }

}
