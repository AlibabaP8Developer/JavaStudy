package com.xiaomi.offer;

import java.util.Stack;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 * 限制：
 * 0 <= 链表长度 <= 10000
 */
public class Offer06Head {

    public static int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;

        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }

        int size = stack.size();
        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            result[i] = stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        MyList list = new MyList();
        list.add(1);
        list.add(3);
        list.add(2);
        int[] ints = reversePrint(new ListNode());
        System.out.println(ints);
        System.out.println(list.get(0).val);
    }

    static class ListNode {        //类名 ：Java类就是一种自定义的数据结构
        int val;            //数据 ：节点数据
        ListNode next;      //对象 ：引用下一个节点对象。在Java中没有指针的概念，Java中的引用和C语言的指针类似

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }
    }

    static class MyList {
        private ListNode head = null; //头节点

        public boolean add(int a) {  //添加新节点
            ListNode newNode = new ListNode(a);//实例化一个新节点a
            if (head == null) { //若头节点为空，新节点赋值给头节点
                head = newNode;
                return true;
            }
            ListNode cur = head;  //若头节点不为空，用cur代替head进行操作。防止修改head的值
            while (cur.next != null) { //遍历到最后一个节点
                cur = cur.next;
            }
            cur.next = newNode ; //让上一个节点的next指向新节点，完成连接
            return true;
        }

        public boolean delete(int a){ //删除指定节点
            if(head == null){  //若头节点为空，返回false
                return false;
            }
            if(head.val == a){
                head = head.next;
                return true;
            }
            ListNode cur = head; //用cur代替head进行操作。防止修改head的值（以下不再解释）
            while (cur.next != null){ //遍历所有节点
                if(cur.next.val == a){//对cur.next节点进行判断，如果是要删除的，直接让cur.next指向被删除节点的next节点。即为：cur.next = cur.next.next;
                    cur.next = cur.next.next;
                    return true;
                }
                cur = cur.next;
            }
            return false;
        }

        public int size(){  //返回节点长度
            int flag = 0;
            ListNode cur = head;
            while(cur != null){
                flag++;
                cur =cur.next;
            }
            return flag;
        }

        public int find(int a){  //查找节点，返回下标
            ListNode cur = head;
            int flag = 0;
            while(cur.next != null){
                if(cur.val != a){
                    flag++;
                    cur = cur.next;
                }
                return flag;
            }
            return -1;
        }

        public ListNode get(int x){ //用下标查找节点
            if(head == null){
                return null;
            }
            ListNode cur = head;
            for (int i = 0; i < x; i++) {
                cur = cur.next;
            }
            return cur;
        }
    }
}
