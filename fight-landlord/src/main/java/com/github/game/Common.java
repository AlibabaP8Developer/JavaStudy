package com.github.game;

import com.github.domain.Poker;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Common {

    //判断牌型
    public static PokerType jugdeType(ArrayList<Poker> list) {
        int len = list.size();
        if (len <= 4) {
            if (list.size() > 0 && Common.getValue(list.get(0)) == Common.getValue(list.get(len - 1))) {
                switch (len) {
                    case 1:
                        return PokerType.c1;
                    case 2:
                        return PokerType.c2;
                    case 3:
                        return PokerType.c3;
                    case 4:
                        return PokerType.c4;
                }
            }

            if (len == 2 && Common.getColor(list.get(1)) == 5)
                return PokerType.c2;
            if (len == 4 && ((Common.getValue(list.get(0)) == Common.getValue(list.get(len - 2)))
                    || Common.getValue(list.get(1)) == Common.getValue(list.get(len - 1))))
                return PokerType.c31;
            else {
                return PokerType.c0;
            }
        }
        if (len >= 5) {
            PokerIndex pokerIndex = new PokerIndex();
            ArrayList<ArrayList<Integer>> indexList = pokerIndex.indexList;
            for (int i = 0; i < 4; i++) {
                indexList.add(new ArrayList<>());
            }
            Common.getMax(pokerIndex, list);
            if (indexList.get(2).size() == 1 && indexList.get(1).size() == 1 && len == 5)
                return PokerType.c32;
            if (indexList.get(3).size() == 1 && len == 6)
                return PokerType.c411;
            if (indexList.get(3).size() == 1 && indexList.get(1).size() == 2 && len == 8)
                return PokerType.c422;
            if ((Common.getColor(list.get(0)) != 5) && (indexList.get(0).size() == len)
                    && (Common.getValue(list.get(0)) - Common.getValue(list.get(len - 1)) == len - 1))
                return PokerType.c123;
            if (indexList.get(1).size() == len / 2 && len % 2 == 0 && len / 2 >= 3
                    && (Common.getValue(list.get(0)) - Common.getValue(list.get(len - 1)) == (len / 2 - 1)))
                return PokerType.c112233;
            if (indexList.get(2).size() == len / 3 && (len % 3 == 0)
                    && (Common.getValue(list.get(0)) - Common.getValue(list.get(len - 1)) == (len / 3 - 1)))
                return PokerType.c111222;
            if (indexList.get(2).size() == len / 4 && (indexList.get(2).get(len / 4 - 1)
                    - (indexList.get(2).get(0)) == len / 4 - 1))
                return PokerType.c11122234;

            if (indexList.get(2).size() == len / 5 && indexList.get(2).size() == len / 5
                    && ((indexList.get(2).get(len / 5 - 1)) - (indexList.get(2).get(0)) == len / 5
                    - 1))
                return PokerType.c1112223344;

        }
        return PokerType.c0;
    }


    //移动牌（有移动的动画效果）
    public static void move(Poker poker, Point from, Point to) {
        if (to.x != from.x) {
            double k = (1.0) * (to.y - from.y) / (to.x - from.x);
            double b = to.y - to.x * k;
            int flag = 0;
            if (from.x < to.x)
                flag = 20;
            else {
                flag = -20;
            }
            for (int i = from.x; Math.abs(i - to.x) > 20; i += flag) {
                double y = k * i + b;

                poker.setLocation(i, (int) y);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        poker.setLocation(to);
    }


    //利用牌的价值，将集合中的牌进行排序
    public static void order(ArrayList<Poker> list) {
        //此时可以改为lambda表达式
        Collections.sort(list, new Comparator<Poker>() {
            @Override
            public int compare(Poker o1, Poker o2) {
                //获取花色
                //1-黑桃 2-红桃 3-梅花 4-方块 5-大小王
                int a1 = Integer.parseInt(o1.getName().substring(0, 1));
                int a2 = Integer.parseInt(o2.getName().substring(0, 1));

                //获取牌上的数字,同时也是牌的价值
                //1-A ... 11-J 12-Q 13-K
                int b1 = Integer.parseInt(o1.getName().substring(2));
                int b2 = Integer.parseInt(o2.getName().substring(2));

                //计算牌的价值，利用牌的价值进行排序
                //牌上的数字在3~10之间，价值就是3~10
                //3:价值3
                //...
                //10:价值10
                //J:价值11
                //Q:价值12
                //K:价值13
                //A：1 + 20 = 21
                //2：2 + 30 = 32
                //小王：1 + 100 = 101
                //大王：2 + 100 = 102

                //计算大小王牌的价值
                if (a1 == 5) {
                    b1 += 100;
                }
                if (a2 == 5) {
                    b2 += 100;
                }

                //计算A的价值
                if (b1 == 1) {
                    b1 += 20;
                }
                if (b2 == 1) {
                    b2 += 20;
                }
                //计算2的价值
                if (b1 == 2) {
                    b1 += 30;
                }
                if (b2 == 2) {
                    b2 += 30;
                }

                //倒序排列
                int flag = b2 - b1;

                //如果牌的价值一样，则按照花色排序
                if (flag == 0) {
                    return a2 - a1;
                } else {
                    return flag;
                }
            }
        });
    }

    //重新摆放牌
    public static void rePosition(GameJFrame m, ArrayList<Poker> list, int flag) {
        Point p = new Point();
        if (flag == 0) {
            p.x = 50;
            p.y = (450 / 2) - (list.size() + 1) * 15 / 2;
        }
        if (flag == 1) {
            p.x = (800 / 2) - (list.size() + 1) * 21 / 2;
            p.y = 450;
        }
        if (flag == 2) {
            p.x = 700;
            p.y = (450 / 2) - (list.size() + 1) * 15 / 2;
        }
        int len = list.size();
        for (int i = 0; i < len; i++) {
            Poker poker = list.get(i);
            Common.move(poker, poker.getLocation(), p);
            m.container.setComponentZOrder(poker, 0);
            if (flag == 1)
                p.x += 21;
            else
                p.y += 15;
        }
    }


    public static int getScore(ArrayList<Poker> list) {
        int count = 0;
        for (int i = 0, len = list.size(); i < len; i++) {
            Poker poker = list.get(i);
            if (poker.getName().substring(0, 1).equals("5")) {
                count += 5;
            }
            if (poker.getName().substring(2).equals("2")) {
                count += 2;
            }
        }
        return count;
    }

    public static int getColor(Poker poker) {
        return Integer.parseInt(poker.getName().substring(0, 1));
    }

    public static int getValue(Poker poker) {
        int i = Integer.parseInt(poker.getName().substring(2));
        if (poker.getName().substring(2).equals("2"))
            i += 13;
        if (poker.getName().substring(2).equals("1"))
            i += 13;
        if (Common.getColor(poker) == 5)
            i += 2;
        return i;
    }

    public static void getMax(PokerIndex pokerIndex, ArrayList<Poker> list) {
        int count[] = new int[14];
        for (int i = 0; i < 14; i++)
            count[i] = 0;

        for (int i = 0, len = list.size(); i < len; i++) {
            if (Common.getColor(list.get(i)) == 5)// 王
                count[13]++;
            else
                count[Common.getValue(list.get(i)) - 1]++;
        }
        ArrayList<ArrayList<Integer>> indexList = pokerIndex.indexList;
        for (int i = 0; i < 14; i++) {
            switch (count[i]) {
                case 1:
                    indexList.get(0).add(i + 1);
                    break;
                case 2:
                    indexList.get(1).add(i + 1);
                    break;
                case 3:
                    indexList.get(2).add(i + 1);
                    break;
                case 4:
                    indexList.get(3).add(i + 1);
                    break;
            }
        }
    }

    public static Model getModel(ArrayList<Poker> list, int[] orders) {
        ArrayList list2 = new ArrayList<>(list);
        Model model = new Model();
        for (int i = 0; i < orders.length; i++)
            showOrders(orders[i], list2, model);
        return model;
    }

    public static void get123(ArrayList<Poker> list, Model model) {
        ArrayList<Poker> del = new ArrayList<>();
        if (list.size() > 0 && (Common.getValue(list.get(0)) < 7 || Common.getValue(list.get(list.size() - 1)) > 10))
            return;
        if (list.size() < 5)
            return;
        ArrayList<Poker> list2 = new ArrayList<>();
        ArrayList<Poker> temp = new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>();
        for (Poker poker : list2) {
            if (integers.indexOf(Common.getValue(poker)) < 0) {
                integers.add(Common.getValue(poker));
                temp.add(poker);
            }
        }
        Common.order(temp);
        for (int i = 0, len = temp.size(); i < len; i++) {
            int k = i;
            for (int j = i; j < len; j++) {
                if (Common.getValue(temp.get(i)) - Common.getValue(temp.get(j)) == j - i) {
                    k = j;
                }
            }
            if (k - i >= 4) {
                String s = "";
                for (int j = i; j < k; j++) {
                    s += temp.get(j).getName() + ",";
                    del.add(temp.get(j));
                }
                s += temp.get(k).getName();
                del.add(temp.get(k));
                model.a123.add(s);
                i = k;
            }
        }
        list.removeAll(del);
    }

    public static void getTwoTwo(ArrayList<Poker> list, Model model) {
        ArrayList<String> del = new ArrayList<>();
        ArrayList<String> l = model.a2;
        if (l.size() < 3)
            return;
        Integer s[] = new Integer[l.size()];
        for (int i = 0, len = l.size(); i < len; i++) {
            String[] name = l.get(i).split(",");
            s[i] = Integer.parseInt(name[0].substring(2, name[0].length()));
        }
        for (int i = 0, len = l.size(); i < len; i++) {
            int k = i;
            for (int j = i; j < len; j++) {
                if (s[i] - s[j] == j - i)
                    k = j;
            }
            if (k - i >= 2) {
                String ss = "";
                for (int j = i; j < k; j++) {
                    ss += l.get(j) + ",";
                    del.add(l.get(j));
                }
                ss += l.get(k);
                model.a112233.add(ss);
                del.add(l.get(k));
                i = k;
            }
        }
        l.removeAll(del);
    }

    public static void getPlane(ArrayList<Poker> list, Model model) {
        ArrayList<String> del = new ArrayList<>();
        ArrayList<String> l = model.a3;
        if (l.size() < 2)
            return;
        Integer s[] = new Integer[l.size()];
        for (int i = 0, len = l.size(); i < len; i++) {
            String[] name = l.get(i).split(",");
            s[i] = Integer.parseInt(name[0].substring(2, name[0].length()));
        }
        for (int i = 0, len = l.size(); i < len; i++) {
            int k = i;
            for (int j = i; j < len; j++) {
                if (s[i] - s[j] == j - i)
                    k = j;
            }
            if (k != i) {
                String ss = "";
                for (int j = i; j < k; j++) {
                    ss += l.get(j) + ",";
                    del.add(l.get(j));
                }
                ss += l.get(k);
                model.a111222.add(ss);
                del.add(l.get(k));
                i = k;
            }
        }
        l.removeAll(del);
    }

    public static void getBoomb(ArrayList<Poker> list, Model model) {
        ArrayList<Poker> del = new ArrayList<>();
        if (list.size() < 1)
            return;
        if (list.size() >= 2 && Common.getColor(list.get(0)) == 5 && Common.getColor(list.get(1)) == 5) {
            model.a4.add(list.get(0).getName() + "," + list.get(1).getName());
            del.add(list.get(0));
            del.add(list.get(1));
        }
        if (Common.getColor(list.get(0)) == 5 && Common.getColor(list.get(1)) != 5) {
            del.add(list.get(0));
            model.a1.add(list.get(0).getName());
        }
        list.removeAll(del);
        for (int i = 0, len = list.size(); i < len; i++) {
            if (i + 3 < len && Common.getValue(list.get(i)) == Common.getValue(list.get(i + 3))) {
                String s = list.get(i).getName() + ",";
                s += list.get(i + 1).getName() + ",";
                s += list.get(i + 2).getName() + ",";
                s += list.get(i + 3).getName();
                model.a4.add(s);
                for (int j = i; j <= i + 3; j++)
                    del.add(list.get(j));
                i = i + 3;
            }
        }
        list.removeAll(del);
    }

    public static void getThree(ArrayList<Poker> list, Model model) {
        ArrayList<Poker> del = new ArrayList<>();
        for (int i = 0, len = list.size(); i < len; i++) {
            if (i + 2 < len && Common.getValue(list.get(i)) == Common.getValue(list.get(i + 2))) {
                String s = list.get(i).getName() + ",";
                s += list.get(i + 1).getName() + ",";
                s += list.get(i + 2).getName();
                model.a3.add(s);
                for (int j = i; j <= i + 2; j++)
                    del.add(list.get(j));
                i = i + 2;
            }
        }
        list.removeAll(del);
    }

    public static void getTwo(ArrayList<Poker> list, Model model) {
        ArrayList<Poker> del = new ArrayList<>();
        for (int i = 0, len = list.size(); i < len; i++) {
            if (i + 1 < len && Common.getValue(list.get(i)) == Common.getValue(list.get(i + 1))) {
                String s = list.get(i).getName() + ",";
                s += list.get(i + 1).getName();
                model.a2.add(s);
                for (int j = i; j <= i + 1; j++)
                    del.add(list.get(j));
                i = i + 1;
            }
        }
        list.removeAll(del);
    }

    public static void getSingle(ArrayList<Poker> list, Model model) {
        ArrayList<Poker> del = new ArrayList<>();
        for (int i = 0, len = list.size(); i < len; i++) {
            model.a1.add(list.get(i).getName());
            del.add(list.get(i));
        }
        list.removeAll(del);
    }

    public static void hideCards(ArrayList<Poker> list) {
        for (int i = 0, len = list.size(); i < len; i++) {
            list.get(i).setVisible(false);
        }
    }

    public static int checkCards(ArrayList<Poker> c, ArrayList<ArrayList<Poker>> current, GameJFrame m) {
        ArrayList<Poker> currentlist;
        if (m.time[0].getText().equals("不要"))
            currentlist = current.get(2);
        else
            currentlist = current.get(0);
        PokerType cType = Common.jugdeType(c);
        PokerType cType2 = Common.jugdeType(currentlist);
        if (cType != PokerType.c4 && c.size() != currentlist.size())
            return 0;
        if (cType != PokerType.c4 && Common.jugdeType(c) != Common.jugdeType(currentlist)) {

            return 0;
        }
        if (cType == PokerType.c4) {
            if (c.size() == 2)
                return 1;
            if (cType2 != PokerType.c4) {
                return 1;
            }
        }

        if (cType == PokerType.c1 || cType == PokerType.c2 || cType == PokerType.c3 || cType == PokerType.c4) {
            if (Common.getValue(c.get(0)) <= Common.getValue(currentlist.get(0))) {
                return 0;
            } else {
                return 1;
            }
        }
        if (cType == PokerType.c123 || cType == PokerType.c112233 || cType == PokerType.c111222) {
            if (Common.getValue(c.get(0)) <= Common.getValue(currentlist.get(0)))
                return 0;
            else
                return 1;
        }
        if (cType == PokerType.c31 || cType == PokerType.c32 || cType == PokerType.c411 || cType == PokerType.c422
                || cType == PokerType.c11122234 || cType == PokerType.c1112223344) {
            List<Poker> a1 = Common.getOrder2(c);
            List<Poker> a2 = Common.getOrder2(currentlist);
            if (Common.getValue(a1.get(0)) < Common.getValue(a2.get(0)))
                return 0;
        }
        return 1;
    }

    public static ArrayList getOrder2(List<Poker> list) {
        ArrayList<Poker> list2 = new ArrayList<>(list);
        ArrayList<Poker> list3 = new ArrayList<>();
        int len = list2.size();
        int a[] = new int[20];
        for (int i = 0; i < 20; i++)
            a[i] = 0;
        for (int i = 0; i < len; i++) {
            a[Common.getValue(list2.get(i))]++;
        }
        int max = 0;
        for (int i = 0; i < 20; i++) {
            max = 0;
            for (int j = 19; j >= 0; j--) {
                if (a[j] > a[max])
                    max = j;
            }

            for (int k = 0; k < len; k++) {
                if (Common.getValue(list2.get(k)) == max) {
                    list3.add(list2.get(k));
                }
            }
            list2.remove(list3);
            a[max] = 0;
        }
        return list3;
    }

    public static void showOrders(int i, ArrayList<Poker> list, Model model) {
        switch (i) {
            case 1:
                Common.getSingle(list, model);
                break;
            case 2:
                Common.getTwo(list, model);
                Common.getTwoTwo(list, model);
                break;
            case 3:
                Common.getThree(list, model);
                Common.getPlane(list, model);
                break;
            case 4:
                Common.getBoomb(list, model);
                break;
            case 5:
                Common.get123(list, model);
                break;
        }
    }
}

class PokerIndex {
    ArrayList<ArrayList<Integer>> indexList = new ArrayList<>();
}