package com.itheima.interview;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * 动态数组
 * 思路：1 数组[1,2,3,4]
 * 2 插入元素5，6，7，8
 * 3 数组大小不够时，创建一个更大的数组，拷贝源数组的值到新数组，然后新数组替代旧数组
 */
public class DynamicArray implements Iterable<Integer> {

    /**
     * 逻辑大小
     */
    private int size = 0;
    /**
     * 容量
     */
    private int capacity = 8;

    /**
     * 默认都会创建一个大小为8的数组，不管用不用都会占用8个空间
     */
    //private int[] array = new int[capacity];
    private int[] array = {};

    /**
     * 在最后添加元素
     *
     * @param element
     */
    public void addLast(int element) {
        // 容量的检查
        checkAndGrouw();
        array[size] = element;
        size++;
    }

    /**
     * 在中间添加元素
     */
    public void add(int index, int element) {
        checkAndGrouw();

        // 添加逻辑
        if (index >= 0 && index < size) {
            // 参数一：原始数组
            // 参数二：从哪个索引开始
            // 参数三：拷贝到哪个数组，原始数组
            // 参数四：向后移动一位，index索引+1
            // 参数五：拷贝的元素个数
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        // 待插入的新元素放入空出来的索引位置
        array[index] = element;
        size++;
    }

    /**
     * 数组扩容
     */
    private void checkAndGrouw() {
        // 容量的检查
        if (size == 0) {
            // 在用的时候再初始化数组长度为8的数组
            array = new int[capacity];
        }

        if (size == capacity) {
            // 进行扩容，扩容1.5倍
            capacity += capacity >> 1;
            // 创建新数组
            int[] newArray = new int[capacity];
            // 复制数组元素
            System.arraycopy(array, 0, newArray, 0, size);
            // 新数组取代旧数组
            array = newArray;
        }
    }

    /**
     * 删除方法
     *
     * @param index 想要删除的元素的索引
     */
    public int remove(int index) { // 假设索引位置有效 [0, size)
        int removed = array[index];
        // 参数一：原始数组
        // 参数二：想要移动的起始索引，index索引后面的元素起始索引
        // 参数三：新数组覆盖原始数组
        // 参数四：index后的元素移动到的位置
        // 参数五：移动几个元素
        if (index < size - 1) { // 判断是否为最后一个元素，如果不是最后元素再向下执行
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        size--;
        return removed;
    }

    /**
     * 按索引查询
     *
     * @param index [0...size)
     */
    public int get(int index) {
        return array[index];
    }

    /**
     * 遍历
     * 函数式接口
     */
    public void foreach(Consumer<Integer> consumer) {
        for (int i = 0; i < size; i++) {
            // System.out.println(array[i]);
            // 提供 array[i]
            // 返回 void
            consumer.accept(array[i]);
        }
    }

    @Test
    public void test() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(10);
        dynamicArray.addLast(20);
        dynamicArray.addLast(30);
        dynamicArray.addLast(40);
        dynamicArray.addLast(50);
        dynamicArray.add(2, 666);
        for (int i = 0; i < 5; i++) {
            int i1 = dynamicArray.get(i);
            System.out.println(i1);
        }
    }

    @Test
    public void test1() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(999);
        dynamicArray.addLast(20);
        dynamicArray.addLast(888);
        dynamicArray.addLast(777);
        dynamicArray.addLast(111);
        dynamicArray.add(2, 666);
        dynamicArray.foreach(System.out::println);
    }

    @Test
    public void test2() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(11);
        dynamicArray.addLast(22);
        dynamicArray.addLast(33);
        dynamicArray.addLast(44);
        dynamicArray.addLast(55);
        dynamicArray.add(1, 888);
        dynamicArray.forEach(System.out::println);// 与下的foreach相同
        //for (Integer element : dynamicArray) {
        //    System.out.println(element);
        //}
    }

    @Test
    public void test3() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(111);
        dynamicArray.addLast(222);
        dynamicArray.addLast(333);
        dynamicArray.addLast(444);
        dynamicArray.addLast(555);
        dynamicArray.addLast(555);
        dynamicArray.addLast(555);
        dynamicArray.addLast(555);
        dynamicArray.addLast(555);
        dynamicArray.addLast(555);
        dynamicArray.foreach(System.out::println);
        System.out.println("----");
        int remove = dynamicArray.remove(4);
        System.out.println("删除索引的元素是：" + remove);
        dynamicArray.forEach(System.out::println);
    }

    @Test
    public void test4() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(111);
        dynamicArray.addLast(222);
        dynamicArray.addLast(333);
        dynamicArray.addLast(444);
        dynamicArray.addLast(555);
        dynamicArray.add(3, 999);
        dynamicArray.stream().forEach(System.out::println);
    }

    /**
     * 流进行遍历
     *
     * @return
     */
    public IntStream stream() {
        // 赋值包含了无效的部分
        //return IntStream.of(array);
        // 复制了只包含有效部分
        return IntStream.of(Arrays.copyOfRange(array, 0, size));
    }

    /**
     * 迭代器遍历
     * implements Iterable<Integer>
     *
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int i = 0;

            /**
             * 是否有下一个元素
             * @return
             */
            @Override
            public boolean hasNext() {
                return i < size;
            }

            /**
             * 返回当前元素，指针移动到下一个元素
             * @return
             */
            @Override
            public Integer next() {
                return array[i++];
            }
        };
    }
}
