package com.itheima.pattern.factory.prototype.test;

/**
 * 浅克隆
 */
public class CitationTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        // 创建原型对象
        Citation citation = new Citation();

        Student student = new Student();
        student.setName("刘季");

        citation.setStudent(student);
        // 克隆奖状对象
        Citation clone = citation.clone();

        clone.getStudent().setName("刘恒");
        // 调用show方法展示奖状
        citation.show();
        clone.show();
    }
}
