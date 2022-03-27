package com.xiaomi.util;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

/**
 * 文件处理工具类
 * @author alibaba
 */
public class FileUtil {
 
    /**
     * 向扩展名前追加指定字符串
     *
     * @param file      文件路径
     * @param replaceTo 将要追加的字符串
     */
    public static void fileNameAddTo(File file, String replaceTo) {
        File[] files = file.listFiles();
        for (File file1 : Objects.requireNonNull(files)) {
            String name = file1.getName();
            // 获取文件扩展名
            String ext = name.substring(name.lastIndexOf("."));
            name = name.substring(0, name.lastIndexOf(".")) + replaceTo + ext;
 
            File newFile = new File(file, name);
            if (!newFile.exists()) {
                file1.renameTo(newFile);
            } else {
                System.out.println("文件不存在！");
            }
        }
    }
 
    /**
     * 文件名修改
     *
     * @param file          文件路径
     * @param replaceSource 源字符串
     * @param replaceTo     将要替换的字符串
     */
    public static void fileNameManage(File file, String replaceSource, String replaceTo) {
        File[] files = file.listFiles();
        for (File file1 : Objects.requireNonNull(files)) {
            String name = file1.getName();
            String replace = name.replace(replaceSource, replaceTo);
            File newFile = new File(file, replace);
            if (!newFile.exists()) {
                file1.renameTo(newFile);
            } else {
                System.out.println("文件不存在！");
            }
        }
    }
 
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/alibaba/Desktop/demo");
        FileUtil.fileNameAddTo(file, "【www.baidu.com】");
        //FileUtil.fileNameManage(file, "【www.baidu.com】", "");
    }
}