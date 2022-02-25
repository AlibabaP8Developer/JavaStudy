package com.atguigu.nio.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

// 通道之间的数据传输
public class FileChannelDemo3 {
    // transferFrom()
    // transferTo() 和 transferFrom() 相反
    public static void main(String[] args) throws Exception {
        // 创建两个FileChannel
        RandomAccessFile file1 = new RandomAccessFile("/Users/lizhenghang/Desktop/01-demo.txt", "rw");
        FileChannel channelFrom = file1.getChannel();

        RandomAccessFile file2 = new RandomAccessFile("/Users/lizhenghang/Desktop/02-demo.txt", "rw");
        FileChannel channelTo = file2.getChannel();

        // fromChannel传输到toChannel
        long position = 0;
        long size = channelFrom.size();
//        channelTo.transferFrom(channelFrom, position, size);
        channelFrom.transferTo(position, size, channelTo);

        file1.close();
        file2.close();
        System.out.println("over!");
    }

}
