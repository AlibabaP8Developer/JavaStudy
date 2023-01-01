package com.github.util;

import java.util.ArrayList;
import java.util.Random;

public class CodeUtil {

    public static String getCode(){
        //1.创建一个集合
        ArrayList<Character> list = new ArrayList<>();//52  索引的范围：0 ~ 51
        //2.添加字母 a - z  A - Z
        for (int i = 0; i < 26; i++) {
            list.add((char)('a' + i));//a - z
            list.add((char)('A' + i));//A - Z
        }
        //3.打印集合
        System.out.println(list);
        //4.生成4个随机字母
        String result = "";
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            //获取随机索引
            int randomIndex = r.nextInt(list.size());
            char c = list.get(randomIndex);
            result = result + c;
        }
        //System.out.println(result);//长度为4的随机字符串

        //5.在后面拼接数字 0~9
        int number = r.nextInt(10);
        //6.把随机数字拼接到result的后面
        result = result + number;
        //System.out.println(result);//ABCD5
        //7.把字符串变成字符数组
        char[] chars = result.toCharArray();//[A,B,C,D,5]
        //8.在字符数组中生成一个随机索引
        int index = r.nextInt(chars.length);
        //9.拿着4索引上的数字，跟随机索引上的数字进行交换
        char temp = chars[4];
        chars[4] = chars[index];
        chars[index] = temp;
        //10.把字符数组再变回字符串
        String code = new String(chars);
        //System.out.println(code);
        return code;
    }
}