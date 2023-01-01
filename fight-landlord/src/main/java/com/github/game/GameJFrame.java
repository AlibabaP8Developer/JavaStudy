package com.github.game;

import com.github.domain.Poker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class GameJFrame extends JFrame implements ActionListener {

    //获取界面中的隐藏容器
    //现在统一获取了，后面直接用就可以了
    public Container container = null;

    //管理抢地主和不抢两个按钮
    JButton landlord[] = new JButton[2];

    //管理出牌和不要两个按钮
    JButton publishCard[] = new JButton[2];

    int dizhuFlag;
    int turn;

    //游戏界面中地主的图标
    JLabel dizhu;


    //集合嵌套集合
    //大集合中有三个小集合
    //小集合中装着每一个玩家当前要出的牌
    //0索引：左边的电脑玩家
    //1索引：中间的自己
    //2索引：右边的电脑玩家
    ArrayList<ArrayList<Poker>> currentList = new ArrayList<>();

    //集合嵌套集合
    //大集合中有三个小集合
    //小集合中装着每一个玩家的牌
    //0索引：左边的电脑玩家
    //1索引：中间的自己
    //2索引：右边的电脑玩家
    ArrayList<ArrayList<Poker>> playerList = new ArrayList<>();

    //底牌
    ArrayList<Poker> lordList = new ArrayList<>();

    //牌盒，装所有的牌
    ArrayList<Poker> pokerList = new ArrayList();

    //三个玩家前方的文本提示
    //0索引：左边的电脑玩家
    //1索引：中间的自己
    //2索引：右边的电脑玩家
    JTextField time[] = new JTextField[3];

    //用户操作，涉及多线程的知识
    PlayerOperation po;

    boolean nextPlayer = false;

    public GameJFrame() {
        //设置图标
        setIconImage(Toolkit.getDefaultToolkit().getImage("image/dizhu.png"));
        //设置界面
        initJframe();
        //添加组件
        initView();
        //界面显示出来
        //先展示界面再发牌，因为发牌里面有动画，界面不展示出来，动画无法展示
        this.setVisible(true);
        //初始化牌
        //准备牌，洗牌，发牌
        new Thread(this::initCard).start();


        //打牌之前的准备工作
        //展示抢地主和不抢地主两个按钮并且再创建三个集合用来装三个玩家准备要出的牌
        initGame();

    }


    //初始化牌
    //准备牌，洗牌，发牌
    public void initCard() {
        //准备牌
        //把所有的牌，包括大小王都添加到牌盒cardList当中
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 13; j++) {
                if ((i == 5) && (j > 2)) {
                    break;
                } else {
                    Poker poker = new Poker(this, i + "-" + j, false);
                    poker.setLocation(350, 150);

                    pokerList.add(poker);
                    container.add(poker);
                }
            }
        }

        //洗牌
        Collections.shuffle(pokerList);

        //创建三个集合用来装三个玩家的牌，并把三个小集合放到大集合中方便管理
        ArrayList<Poker> player0 = new ArrayList<>();
        ArrayList<Poker> player1 = new ArrayList<>();
        ArrayList<Poker> player2 = new ArrayList<>();

        for (int i = 0; i < pokerList.size(); i++) {
            //获取当前遍历的牌
            Poker poker = pokerList.get(i);

            //发三张底牌
            if (i <= 2) {
                //移动牌
                Common.move(poker, poker.getLocation(), new Point(270 + (75 * i), 10));
                //把底牌添加到集合中
                lordList.add(poker);
                continue;
            }
            //给三个玩家发牌
            if (i % 3 == 0) {
                //给左边的电脑发牌
                Common.move(poker, poker.getLocation(), new Point(50, 60 + i * 5));
                player0.add(poker);
            } else if (i % 3 == 1) {
                //给中间的自己发牌
                Common.move(poker, poker.getLocation(), new Point(180 + i * 7, 450));
                player1.add(poker);
                //把自己的牌展示正面
                poker.turnFront();

            } else if (i % 3 == 2) {
                //给右边的电脑发牌
                Common.move(poker, poker.getLocation(), new Point(700, 60 + i * 5));
                player2.add(poker);
            }
            //把三个装着牌的小集合放到大集合中方便管理
            playerList.add(player0);
            playerList.add(player1);
            playerList.add(player2);

            //把当前的牌至于最顶端，这样就会有牌依次错开且叠起来的效果
            container.setComponentZOrder(poker, 0);

        }

        //给牌排序
        for (int i = 0; i < 3; i++) {
            //排序
            Common.order(playerList.get(i));
            //重新摆放顺序
            Common.rePosition(this, playerList.get(i), i);
        }





    }

    //打牌之前的准备工作
    private void initGame() {
        //创建三个集合用来装三个玩家准备要出的牌
        for (int i = 0; i < 3; i++) {
            ArrayList<Poker> list = new ArrayList<>();
            //添加到大集合中方便管理
            currentList.add(list);
        }

        //展示抢地主和不抢地主两个按钮
        landlord[0].setVisible(true);
        landlord[1].setVisible(true);

        //展示自己前面的倒计时文本
        time[1].setVisible(true);
        //倒计时10秒
        po = new PlayerOperation(this, 30);
        //开启倒计时
        po.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == landlord[0]) {
            //点击抢地主
            time[1].setText("抢地主");
            po.isRun = false;
        } else if (e.getSource() == landlord[1]) {
            //点击不抢
            time[1].setText("不抢");
            po.isRun = false;
        } else if (e.getSource() == publishCard[1]) {
            //点击不要
            this.nextPlayer = true;
            currentList.get(1).clear();
            time[1].setText("不要");
        } else if (e.getSource() == publishCard[0]) {
            //点击出牌

            //创建一个临时的集合，用来存放当前要出的牌
            ArrayList<Poker> c = new ArrayList<>();
            //获取中自己手上所有的牌
            ArrayList<Poker> player2 = playerList.get(1);

            //遍历手上的牌，把要出的牌都放到临时集合中
            for (int i = 0; i < player2.size(); i++) {
                Poker poker = player2.get(i);
                if (poker.isClicked()) {
                    c.add(poker);
                }
            }

            int flag = 0;
            //判断，如果左右两个电脑玩家是否都不要
            if (time[0].getText().equals("不要") && time[2].getText().equals("不要")) {
                if (Common.jugdeType(c) != PokerType.c0){
                    flag = 1;
                }
            } else {
                flag = Common.checkCards(c, currentList, this);
            }

            if (flag == 1) {
                //把当前要出的牌，放到大集合中统一管理
                currentList.set(1, c);
                //在手上的牌中，去掉已经出掉的牌
                player2.removeAll(c);

                //计算坐标并移动牌
                //移动的目的是要出的牌移动到上方
                Point point = new Point();
                point.x = (770 / 2) - (c.size() + 1) * 15 / 2;
                point.y = 300;
                for (int i = 0, len = c.size(); i < len; i++) {
                    Poker poker = c.get(i);
                    Common.move(poker, poker.getLocation(), point);
                    point.x += 15;
                }

                //重新摆放剩余的牌
                Common.rePosition(this, player2, 1);
                //赢藏文本提示
                time[1].setVisible(false);
                //下一个玩家可玩
                this.nextPlayer = true;
            }

        }
    }

    //添加组件
    public void initView() {
        //创建抢地主的按钮
        JButton robBut = new JButton("抢地主");
        //设置位置
        robBut.setBounds(320, 400, 75, 20);
        //添加点击事件
        robBut.addActionListener(this);
        //设置隐藏
        robBut.setVisible(false);
        //添加到数组中统一管理
        landlord[0] = robBut;
        //添加到界面中
        container.add(robBut);

        //创建不抢的按钮
        JButton noBut = new JButton("不     抢");
        //设置位置
        noBut.setBounds(420, 400, 75, 20);
        //添加点击事件
        noBut.addActionListener(this);
        //设置隐藏
        noBut.setVisible(false);
        //添加到数组中统一管理
        landlord[1] = noBut;
        //添加到界面中
        container.add(noBut);

        //创建出牌的按钮
        JButton outCardBut = new JButton("出牌");
        outCardBut.setBounds(320, 400, 60, 20);
        outCardBut.addActionListener(this);
        outCardBut.setVisible(false);
        publishCard[0] = outCardBut;
        container.add(outCardBut);

        //创建不要的按钮
        JButton noCardBut = new JButton("不要");
        noCardBut.setBounds(420, 400, 60, 20);
        noCardBut.addActionListener(this);
        noCardBut.setVisible(false);
        publishCard[1] = noCardBut;
        container.add(noCardBut);


        //创建三个玩家前方的提示文字：倒计时
        //每个玩家一个
        //左边的电脑玩家是0
        //中间的自己是1
        //右边的电脑玩家是2
        for (int i = 0; i < 3; i++) {
            time[i] = new JTextField("倒计时:");
            time[i].setEditable(false);
            time[i].setVisible(false);
            container.add(time[i]);
        }
        time[0].setBounds(140, 230, 60, 20);
        time[1].setBounds(374, 360, 60, 20);
        time[2].setBounds(620, 230, 60, 20);


        //创建地主图标
        dizhu = new JLabel(new ImageIcon("image/dizhu.png"));
        dizhu.setVisible(false);
        dizhu.setSize(40, 40);
        container.add(dizhu);



    }

    //设置界面
    public void initJframe() {
        //设置标题
        this.setTitle("斗地主");
        //设置大小
        this.setSize(830, 620);
        //设置关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口无法进行调节
        this.setResizable(false);
        //界面居中
        this.setLocationRelativeTo(null);
        //获取界面中的隐藏容器，以后直接用无需再次调用方法获取了
        container = this.getContentPane();
        //取消内部默认的居中放置
        container.setLayout(null);
        //设置背景颜色
        container.setBackground(Color.LIGHT_GRAY);
    }

}
