package com.github.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;

/**
 * 生成压缩文件 （zip，rar 格式）
 *
 * @author XuPengFei
 */
public class CompressUtil {

    /**
     * @param sourcePath 要压缩的文件路径
     * @param suffix     生成的格式后最（zip、rar）
     */
    public static void generateFile(String sourcePath, String suffix) throws Exception {

        File file = new File(sourcePath);
        // 压缩文件的路径不存在
        if (!file.exists()) {
            throw new Exception("路径 " + sourcePath + " 不存在文件，无法进行压缩...");
        }
        // 用于存放压缩文件的文件夹
        String generateFile = file.getParent() + File.separator + "CompressFile";
        File compress = new File(generateFile);
        // 如果文件夹不存在，进行创建
        if (!compress.exists()) {
            compress.mkdirs();
        }
        // 目的压缩文件
        String generateFileName = compress.getAbsolutePath() + File.separator + "AAA" + file.getName() + "." + suffix;

        // 输入流 表示从一个源读取数据
        // 输出流 表示向一个目标写入数据

        // 输出流
        FileOutputStream outputStream = new FileOutputStream(generateFileName);

        // 压缩输出流
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(outputStream));

        generateFile(zipOutputStream, file, "");

        System.out.println("源文件位置：" + file.getAbsolutePath() + "，目的压缩文件生成位置：" + generateFileName);
        // 关闭 输出流
        zipOutputStream.close();
    }

    /**
     * @param out  输出流
     * @param file 目标文件
     * @param dir  文件夹
     * @throws Exception
     */
    private static void generateFile(ZipOutputStream out, File file, String dir) throws Exception {

        // 当前的是文件夹，则进行一步处理
        if (file.isDirectory()) {
            //得到文件列表信息
            File[] files = file.listFiles();

            //将文件夹添加到下一级打包目录
            out.putNextEntry(new ZipEntry(dir + "/"));

            dir = dir.length() == 0 ? "" : dir + "/";

            //循环将文件夹中的文件打包
            for (int i = 0; i < files.length; i++) {
                generateFile(out, files[i], dir + files[i].getName());
            }

        } else { // 当前是文件

            // 输入流
            FileInputStream inputStream = new FileInputStream(file);
            // 标记要打包的条目
            out.putNextEntry(new ZipEntry(dir));
            // 进行写操作
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) > 0) {
                out.write(bytes, 0, len);
            }
            // 关闭输入流
            inputStream.close();
        }

    }

    /**
     * 递归压缩文件
     *
     * @param output    ZipOutputStream 对象流
     * @param file      压缩的目标文件流
     * @param childPath 条目目录
     */
    private static void zip(ZipOutputStream output, File file, String childPath) {
        FileInputStream input = null;
        try {
            // 文件为目录
            if (file.isDirectory()) {
                // 得到当前目录里面的文件列表
                File[] list = file.listFiles();
                childPath = childPath + (childPath.length() == 0 ? "" : "/")
                        + file.getName();
                // 循环递归压缩每个文件
                for (File f : list) {
                    zip(output, f, childPath);
                }
            } else {
                // 压缩文件
                childPath = (childPath.length() == 0 ? "" : childPath + "/")
                        + file.getName();
                output.putNextEntry(new ZipEntry(childPath));
                input = new FileInputStream(file);
                int readLen = 0;
                byte[] buffer = new byte[1024 * 8];
                while ((readLen = input.read(buffer, 0, 1024 * 8)) != -1) {
                    output.write(buffer, 0, readLen);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // 关闭流
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    /**
     * 压缩文件（文件夹）
     *
     * @param path   目标文件流
     * @param format zip 格式 | rar 格式
     * @throws Exception
     */
    public static String zipFile(File path, String format) throws Exception {
        String generatePath = "";
        if (path.isDirectory()) {
            generatePath = path.getParent().endsWith("/") == false ? path.getParent() + File.separator + path.getName() + "." + format : path.getParent() + path.getName() + "." + format;
        } else {
            generatePath = path.getParent().endsWith("/") == false ? path.getParent() + File.separator : path.getParent();
            generatePath += path.getName().substring(0, path.getName().lastIndexOf(".")) + "." + format;
        }
        // 输出流
        FileOutputStream outputStream = new FileOutputStream(generatePath);
        // 压缩输出流
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(outputStream));
        zip(out, path, "");
        out.flush();
        out.close();

        return generatePath;
    }
}
