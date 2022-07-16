package com.gtihub;

import java.util.Random;

public class test {
    public static void main(String[] args) {
        // 需求
        // 把一个一维数组中的数据：0-15打乱顺序
        // 然后再按照4个一组的方式添加到二维数组当中
        // 1 定义一个一维数组
        int[] temp = {0, 2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        // 2 打乱数组中的数据的顺序
        // 遍历数组，得到每一个元素，拿着每一个元素跟随机索引上的数据进行交换
        Random random = new Random();
        for (int i = 0; i < temp.length; i++) {
            // 获取到随机索引
            int index = random.nextInt(temp.length);
            // 拿着遍历到的每一个数据，跟随机索引上的数据进行交换
            int t = temp[i];
            temp[i] = temp[index];
            temp[index] = t;
        }

        // 遍历
        for (int i = 0; i < temp.length; i++) {
            System.out.print(temp[i] + "  ");
        }

        System.out.println();

        // 创建一个二维数组
        int[][] data = new int[4][4];
        /*
            给二维数组添加数据
            解法一：
            遍历一维数组temp得到每一个元素，把每一个元素依次添加到二维数组中
         */
        System.out.println("解法一：");
        for (int i = 0; i < temp.length; i++) {
            data[i / 4][i % 4] = temp[i];
        }

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j]+"  ");
            }
            System.out.println();
        }

        System.out.println("解法二：");

        int index = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = temp[index];
                index++;
            }
        }

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j]+"  ");
            }
            System.out.println();
        }
    }
}
