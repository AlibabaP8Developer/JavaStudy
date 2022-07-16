package com.github.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * 界面窗体
 */
public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    JMenuItem rePlayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登陆");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("官方网站");

    // 创建一个二维数组,管理数据
    // 加载图片时，会根据二维数组中的数据进行加载
    int[][] data = new int[4][4];
    // 记录空白方块位置
    int x = 0;
    int y = 0;
    // 定义一个变量，记录当前展示图片的路径
    String path = "src/main/resources/image/girl/girl4/";
    // 定义一个二维数组，存储正确的数据
    int[][] wim = {{1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}};

    // 定义变量，用于统计步数
    int step = 0;

    public GameJFrame() throws HeadlessException {
        // 初始化界面
        initJFame();

        initJMenuBar();

        // 初始化数据（打乱）
        initData();

        // 初始化图片（根据打乱之后的结果去加载图片）
        initImage();

        this.setVisible(true);
    }

    /**
     * 初始化数据（打乱）
     */
    private void initData() {
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

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = temp[i];
        }
    }

    /**
     * 初始化图片（根据打乱之后的结果去加载图片）
     * 添加图片时，需要按照二维数组中管理的数据添加图片
     */
    private void initImage() {

        // 清空原本已经出现的所有图片
        this.getContentPane().removeAll();
        boolean victory = victory();
        if (victory) {
            // 显示胜利图标
            ImageIcon bg = new ImageIcon("src/main/resources/image/win.png");
            JLabel wimLabel = new JLabel(bg);
            wimLabel.setBounds(203, 283, 197, 73);
            // 把背景图片添加到界面中
            this.getContentPane().add(wimLabel);
        }

        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);

        /*
            先加载的图片在上方，后加载的图片在下面
         */
        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {

                // 获取当前要加载图片的序号
                int num = data[i][j];
                // 创建一个图片imageIcon的对象
                // 创建一个jLabel的对象（管理容器）
                JLabel label = new JLabel(new ImageIcon(path + num + ".jpg"));
                // 指定图片位置
                label.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                // 给图片添加边框
                label.setBorder(new BevelBorder(BevelBorder.RAISED));
                // 把管理容器添加到界面中, 创建JFrame后隐藏的容器
                this.getContentPane().add(label);
            }

            // 刷新一下界面
            this.getContentPane().repaint();
        }

        // 添加背景图片
        ImageIcon bg = new ImageIcon("src/main/resources/image/background.png");
        JLabel bgLabel = new JLabel(bg);
        bgLabel.setBounds(40, 40, 508, 560);
        // 把背景图片添加到界面中
        this.getContentPane().add(bgLabel);
    }

    private void initJMenuBar() {
        // 初始化菜单
        // 创建整个的菜单对象
        JMenuBar menuBar = new JMenuBar();

        // 创建菜单上面的两个选项的对象
        JMenu functionMenu = new JMenu("功能");
        JMenu aboutMenu = new JMenu("关于我们");

        // 创建选项下面的条目对象
        functionMenu.add(rePlayItem);
        functionMenu.add(reLoginItem);
        functionMenu.add(closeItem);
        aboutMenu.add(accountItem);

        // 将菜单里面的两个选项添加到菜单当中
        menuBar.add(functionMenu);
        menuBar.add(aboutMenu);

        // 给条目绑定事件
        rePlayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        // 给整个界面设置菜单
        this.setJMenuBar(menuBar);
    }

    private void initJFame() {
        this.setSize(603, 680);
        this.setTitle("拼图单机版 v1.0");
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 取消默认的居中方式
        this.setLayout(null);
        // 给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 按下不松
        int keyCode = e.getKeyCode();
        if (keyCode == 65) {
            // 把界面中所有图片删除
            this.getContentPane().removeAll();
            // 加载完整图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            // 加载背景图片
            ImageIcon bg = new ImageIcon("src/main/resources/image/background.png");
            JLabel bgLabel = new JLabel(bg);
            bgLabel.setBounds(40, 40, 508, 560);
            this.getContentPane().add(bgLabel);
            // 刷新界面
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 判断游戏是否胜利，此方法直接结束
        boolean victory = victory();
        if (victory) {
            // 1.返回结果，2.结束方法
            return;
        }
        // 松开
        // 对上下左右进行判断
        // 左：37，右：39，上：38，下：40
        int keyCode = e.getKeyCode();
        if (keyCode == 37) {
            System.out.println("向左移动");
            if (y == 3) {
                // 表示空白方块已经在最下方了，他的下面没有图片再移动了
                return;
            }
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            // 每移动一次计数器，自增一次
            step++;
            // 调用方法按照最新数字加载图片
            initImage();
        } else if (keyCode == 38) {
            System.out.println("向上移动");
            if (x == 3) {
                // 表示空白方块已经在最下方了，他的下面没有图片再移动了
                return;
            }
            // 空白方块下方的数字向上移动
            // x，y 表示空白方块
            // x+1，y 表示空白方块下方数字
            // 空白方块下方的数字赋值给空白方块
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            // 每移动一次计数器，自增一次
            step++;
            // 调用方法按照最新数字加载图片
            initImage();
        } else if (keyCode == 39) {
            System.out.println("向右移动");
            if (y == 0) {
                // 表示空白方块已经在最下方了，他的下面没有图片再移动了
                return;
            }
            // x, y+1
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            // 每移动一次计数器，自增一次
            step++;
            // 调用方法按照最新数字加载图片
            initImage();
        } else if (keyCode == 40) {
            System.out.println("向下移动");
            if (x == 0) {
                // 表示空白方块已经在最下方了，他的下面没有图片再移动了
                return;
            }
            // 把空白方块上方的数字向下移动
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            // 每移动一次计数器，自增一次
            step++;
            // 调用方法按照最新数字加载图片
            initImage();
        } else if (keyCode == 65) {
            // 查看完整图片 w
            initImage();
        } else if (keyCode == 87) {
            // 一键胜利
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }
    }

    /**
     * 判断data数组中的数据是否跟win数组相同
     * 如果全部相同，返回true，否则返回false
     *
     * @return
     */
    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            // i:依次表示二维数组，data里面索引
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != wim[i][j]) {
                    // 只要有一个数据不一样，返回false
                    return false;
                }
            }
        }
        // 循环结束表示数组遍历比较完毕，全部一样返回true
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取当前被点击的条目对象
        Object source = e.getSource();
        if (source == rePlayItem) {
            // 重新游戏
            // 再次打乱二维数组数据
            initData();
            // 计步器清零
            step = 0;
            // 重新加载图片
            initImage();
        } else if (source == closeItem) {
            // 关闭游戏
            System.exit(0);
        } else if (source == accountItem) {
            // 官方网站
            JDialog dialog = new JDialog();
            JLabel label = new JLabel(new ImageIcon("src/main/resources/image/about.png"));
            label.setBounds(0, 0, 258, 258);
            // 把图片添加到弹框中
            dialog.getContentPane().add(label);
            // 给弹框设置大小
            dialog.setSize(344,344);
            // 置顶
            dialog.setAlwaysOnTop(true);
            // 弹框居中
            dialog.setLocationRelativeTo(null);
            // 弹框不关闭则无法操作下面的界面
            dialog.setModal(true);
            dialog.setVisible(true);
        } else if (source == reLoginItem) {
            // 重新登陆
            this.setVisible(false);
            new LoginJFame();
        }
    }
}
