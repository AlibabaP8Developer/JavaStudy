package com.atguigu.nio.channel;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo1 {
    // 从filechannel读取数据到buffer中
    public static void main(String[] args) throws Exception {
        // 创建fileChannel
        RandomAccessFile file = new RandomAccessFile("/Users/lizhenghang/Desktop/01-demo.txt",
                "rw");
        FileChannel channel = file.getChannel();

        // 创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 读取数据到buffer中
        int bytesRead = channel.read(buffer);
        // byteRead == -1 表示结束
        while (bytesRead != -1) {
            System.out.println("读取了" +bytesRead);
            buffer.flip();
            // buffer.hasRemaining() 表示buffer中是否有剩余的内容
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();
            bytesRead = channel.read(buffer);
        }
        file.close();
        System.out.println("结束了...");

    }
}
