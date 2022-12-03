package com.itheima.principles.demo2.before;

/**
 * 里氏代换原则
 *  任何基类可以出现的地方，子类一定可以出现。
 *  通俗理解：子类可以扩展父类的功能，但不能改变父类原有的功能。
 *  换句话说，子类继承父类时，除添加新的方法完成新增功能外，尽量不要重写父类的方法。
 *  基类可以使用，子类也可以使用
 *  本案例：正方形是子类 不可以使用，长方形是父类 可以使用，违反了里氏代换原则
 */
public class RectangleDemo {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(10);
        rectangle.setLength(20);
        resize(rectangle);
        printLengthWidth(rectangle);
        System.out.println("=======");
        // 创建正方形对象
        Square square = new Square();
        // 设置长和宽
        square.setLength(10);
        // 调用resize进行扩宽
        resize(square);
        printLengthWidth(square);
    }

    // 扩宽方法
    public static void resize(Rectangle rectangle) {
        // 判断宽比长小， 进行扩宽操作
        while (rectangle.getWidth() <= rectangle.getLength()) {
            rectangle.setWidth(rectangle.getWidth() + 1);
        }
    }

    // 打印长宽
    public static void printLengthWidth(Rectangle rectangle) {
        System.out.println("长："+rectangle.getLength());
        System.out.println("宽："+rectangle.getWidth());
    }
}
