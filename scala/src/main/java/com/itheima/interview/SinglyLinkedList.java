package com.itheima.interview;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 单向链表
 */
public class SinglyLinkedList implements Iterable<Integer> {

    private Node head = null; // head头指针 可以找到第一个元素

    /**
     * 第三种遍历方式：实现Iterable<Integer>
     *
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        // 匿名内部类 -> 带名字的内部类
        return new Iterator<Integer>() {
            Node p = head;

            /**
             * 询问是否有下一个元素
             * @return
             */
            @Override
            public boolean hasNext() {
                return p != null;
            }

            /**
             * 返回当前元素的值，并指向下一个元素
             * @return
             */
            @Override
            public Integer next() {
                int v = p.value;
                p = p.next;
                return v;
            }
        };
    }

    /**
     * 节点类
     */
    private static class Node {
        int value; // 值
        Node next;// 下一个节点指针

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public void addFirst(int value) {
        // 1.链表为空
        //head = new Node(value, null);
        // 2.链表非空
        head = new Node(value, head);
    }

    private Node findLast() {
        if (head == null) { // 空链表
            return null;
        }

        Node p;
        for (p = head; p.next != null; p = p.next) {

        }
        return p;
    }

    public void addLast(int value) {
        Node last = findLast();

        if (last == null) {
            addFirst(value);
            return;
        }

        // 下一个指针指向新节点  新节点为null
        last.next = new Node(value, null);
    }

    /**
     * 遍历链表
     */
    public void loop() {
        Node p = head;
        while (p != null) {
            System.out.println(p.value);
            p = p.next;
        }
    }

    public void loop1(Consumer<Integer> consumer) {
        Node p = head;
        while (p != null) {
            consumer.accept(p.value);
            p = p.next;
        }
    }

    /**
     * 遍历链表2
     */
    public void loop2(Consumer<Integer> consumer) {
        for (Node p = head; p != null; p = p.next) {
            consumer.accept(p.value);
        }
    }

    public void test() {
        int i = 0;
        for (Node p = head; p != null; p = p.next, i++) {
            System.out.println(p.value + "索引是：" + i);
        }
    }

    private Node findNode(int index) {
        int i = 0;
        for (Node p = head; p != null; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        // 没有找到
        return null;
    }

    public int get(int index) {
        Node node = findNode(index);
        if (node == null) {
            // 抛出异常
            throw new IllegalArgumentException(String.format("index [%d]", index));
        }
        return node.value;
    }

    public static void main1(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        //list.loop2(System.out::println);

        for (Integer i : list) {
            System.out.println(i);
        }

    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.test();
    }
}

