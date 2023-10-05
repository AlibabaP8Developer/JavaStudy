package com.itheima.interview;

/**
 * 动态数组
 * 思路：1 数组[1,2,3,4]
 * 2 插入元素5，6，7，8
 * 3 数组大小不够时，创建一个更大的数组，拷贝源数组的值到新数组，然后新数组替代旧数组
 */
public class DynamicArray {

    /**
     * 逻辑大小
     */
    private int size = 0;
    /**
     * 容量
     */
    private int capacity = 8;

    private int[] array = new int[capacity];

    /**
     * 在最后添加元素
     *
     * @param element
     */
    public void addLast(int element) {
        array[size] = element;
        size++;
    }

    /**
     * 在中间添加元素
     */
    public void add(int index, int element) {
        if (index>=0 && index<size) {
            // 参数一：原始数组
            // 参数二：从哪个索引开始
            // 参数三：拷贝到哪个数组，原始数组
            // 参数四：向后移动一位，index索引+1
            // 参数五：拷贝的元素个数
            System.arraycopy(array, index, array, index + 1, size - index);
            // 待插入的新元素放入空出来的索引位置
            array[index] = element;
            size++;
        } else if (index == size) { // addLast
            array[size] = element;
            size++;
        }
    }

    public void test() {
        this.array = new int[]{1, 2, 3, 4, 5, 6};
        for (int i : this.array) {
            System.out.println("源数组："+i);
        }
        new DynamicArray().add(2, 6);

    }

    public static void main(String[] args) {
        new DynamicArray().test();
    }
}
