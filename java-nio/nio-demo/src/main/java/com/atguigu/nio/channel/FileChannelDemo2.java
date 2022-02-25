package com.atguigu.nio.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

// FileChannel写操作
public class FileChannelDemo2 {
    // 从filechannel读取数据到buffer中
    public static void main(String[] args) throws Exception {
        // 打开FileChannel
        RandomAccessFile file = new RandomAccessFile("/Users/lizhenghang/Desktop/01-demo.txt",
                "rw");
        FileChannel channel = file.getChannel();

        // 创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String name = "大明皇帝朱元璋";
        buffer.clear();

        // 写入内容
        buffer.put(name.getBytes(StandardCharsets.UTF_8));
        // buffer读写转换
        buffer.flip();
        // FileChannel完成最终的实现
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
        channel.close();
    }
}
