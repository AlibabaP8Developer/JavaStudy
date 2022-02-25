package com.atguigu.nio.path;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo1 {

    public static void main(String[] args) {
        // 创建path实例
        Path path = Paths.get("/Users/lizhenghang/Desktop/demo/01-demo.txt");

        // 创建相对路径
        Path projects  = Paths.get("/Users/lizhenghang/Desktop/demo", "projects");
        Path file  = Paths.get("/Users/lizhenghang/Desktop/demo", "projects/02-demo.txt");

        String originalPath = "/Users/lizhenghang/Desktop/demo";

        Path path1 = Paths.get(originalPath);
        System.out.println("path1:"+path1);

        Path path2 = path1.normalize();
        System.out.println("path2:"+path2);


    }
}
