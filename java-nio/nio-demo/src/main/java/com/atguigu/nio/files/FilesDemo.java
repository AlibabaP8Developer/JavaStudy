package com.atguigu.nio.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesDemo {
    public static void main(String[] args) throws Exception {
        Path path1 = Paths.get("/Users/lizhenghang/Desktop/demo/01-demo.txt");
        Path path2 = Paths.get("/Users/lizhenghang/Desktop/demo1/02-demo.txt");
//        Path directories = Files.createDirectories(path1);
        Path copy = Files.copy(path1, path2);

    }
}
