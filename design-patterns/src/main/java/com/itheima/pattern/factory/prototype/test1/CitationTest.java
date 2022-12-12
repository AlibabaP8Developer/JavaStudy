package com.itheima.pattern.factory.prototype.test1;

import java.io.*;

/**
 * 深克隆
 */
public class CitationTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        // 创建原型对象
        Citation citation = new Citation();

        Student student = new Student();
        student.setName("刘季");

        citation.setStudent(student);

        // 创建对象输出流对象
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/lizhenghang/Desktop/a.txt"));
            // 写对象
            oos.writeObject(citation);
            // 释放资源
            oos.close();
            // 创建对象输入流对象
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/lizhenghang/Desktop/a.txt"));
            // 读取对象
            Citation citation1 = (Citation) ois.readObject();
            ois.close();
            Student student1 = citation1.getStudent();
            student1.setName("姜太公");
            citation.show();
            citation1.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
