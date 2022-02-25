package com.atguigu.nio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 二分查找
 */
@SpringBootTest
class NioDemoApplicationTests {

    /**
     * 甲. 前提：有已排序数组A（假设已经做好）
     * 乙. 定义左边界L，左边界R，确定搜索范围，循环执行二分查找
     * 丙. 获取中间索引M=Floor（（L+R）/2）
     * 丁. 中间索引的值A[M]与待搜索的值T进行比较
     * a. A[M]==T表示找到，返回中间索引
     * b. A[M]>T，中间值右侧的其它元素都大于T，无需比较，中间索引左边去找，M-1设置为右边界，重新查找
     * c. A[M]<T，中间值左侧的其它元素都小于T，无需比较，中间索引右边去找，M+1设置为左边界，重新查找
     * L>R时，表示没有找到，应结束循环
     */
    @Test
    public void contextLoads() {
        int[] array = {1, 5, 8, 11, 19, 22, 31, 35, 40, 45, 48, 49, 50};
        int target = 48;
        int idx = binarySearch(array, target);
        System.out.println(idx);
    }

    private static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        int middle;
        while (left <= right) {
            middle = (left + right) / 2;// 7
            if (array[middle] == target) {
                return middle;
            } else if (array[middle] > target) {
                // 右边的都不需要比，只需要和左边相比 设置右边界
                right = middle - 1;
            } else {
                left = middle + 1;// 7+1=8
            }
        }
        return -1;
    }

}
