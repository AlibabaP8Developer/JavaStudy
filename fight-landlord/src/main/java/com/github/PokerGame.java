package com.github;

import java.util.*;

public class PokerGame {

    /**
     * 牌盒
     */
    static ArrayList<String> list = new ArrayList<>();

    /**
     * 牌的价值
     */
    static Map<String, Integer> map = new HashMap<String, Integer>();

    //静态代码块
    // 特点：随着类的加载而在加载的，而且只执行一次
    static {
        //准备牌
        //♦♣♥♠
        //3，4，5，6，7，8，9，10，J，Q，K，A，2
        String[] color = {"♦", "♣", "♥", "♠"};
        String[] number = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        //牌盒
        for (String c : color) {
            // c依次表示每一种花色
            for (String n : number) {
                // n每个数字
                list.add(c + n);
            }
        }
        list.add(" 大王");
        list.add(" 小王");

        // 指定牌的价值
        // 牌上的数字到map集合中判断是否存在
        // 存在，获取价值；不存在，本身数字就是价值
        map.put("J", 11);
        map.put("Q", 12);
        map.put("K", 13);
        map.put("A", 14);
        map.put("2", 15);
        map.put("小王", 50);
        map.put("大王", 100);
    }

    public PokerGame() {
        //洗牌 打乱顺序
        Collections.shuffle(list);
        System.out.println(list);
        System.out.println(list.size());

        //发牌 底牌 4个集合
        ArrayList<String> lord = new ArrayList<>();
        ArrayList<String> player1 = new ArrayList<>();
        ArrayList<String> player2 = new ArrayList<>();
        ArrayList<String> player3 = new ArrayList<>();

        // 遍历牌盒 发牌记录牌的序号
        for (int i = 0; i < list.size(); i++) {
            // i: 序号
            String poker = list.get(i);
            if (i <= 2) {
                // 0, 1, 2 前三张底牌
                lord.add(poker);
                continue;
            }
            // 给三个玩家轮流发牌
            // i % 3 == 1
            if (i % 3 == 0) {
                player1.add(poker);
            } else if (i % 3 == 1) {
                player2.add(poker);
            } else {
                player3.add(poker);
            }
        }

        // 玩家牌进行排序
        order(lord);
        order(player1);
        order(player2);
        order(player3);

        //看牌
        lockPoker("底牌", lord);
        lockPoker("朱元璋", player1);
        lockPoker("赵匡胤", player2);
        lockPoker("刘 季", player3);
    }

    /**
     * @param name 玩家名字
     * @param list 玩家的牌
     */
    public void lockPoker(String name, ArrayList<String> list) {
        System.out.print(name + ":");
        for (String poker : list) {
            System.out.print(poker + " ");
        }
        System.out.println();
    }

    /**
     * 利用牌的价值排序
     *
     * @param list
     */
    public void order(ArrayList<String> list) {
        // Array.sort 插入排序+二分查找
        Collections.sort(list, (o1, o2) -> {
            //System.out.println("o1: " + o1);
            //System.out.println("o2: " + o2);

            // o1 表示当前要插入到有序序列中的牌
            // o2 表示已经在有序序列中存在的牌
            // 负数: o1小 插入到前面
            // 正数:  o1大  插入到后面
            // 0 o1的数字跟o2一样，需要按花色进行排序
            // 1 计算o1的花色和价值
            String color1 = o1.substring(0, 1);
            int value1 = getValue(o1);

            // 2 计算o2的花色和价值
            String color2 = o2.substring(0, 1);
            int value2 = getValue(o2);

            // 3 比较o1和o2的价值
            var i = value1 - value2;

            return i == 0 ? color1.compareTo(color2) : i;
        });

    }

    /**
     * 计算牌的价值
     * 参数：牌
     * 返回值 价值
     * @param poker  o1: 大王
     * @return
     */
    public int getValue(String poker) {
        // 获取牌上的数字
        String number = poker.substring(1); // 王
        // 拿着数字到map集合中判断是否存在
        // 存在，获取价值；不存在，类型转换
        if (map.containsKey(number)) {
            return map.get(number);
        } else {
            return Integer.parseInt(number);
        }
    }
}
