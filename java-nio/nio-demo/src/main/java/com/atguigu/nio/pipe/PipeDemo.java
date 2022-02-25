package com.atguigu.nio.pipe;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;

public class PipeDemo {

    public static void main(String[] args) throws Exception {
        // 1.获取管道
        Pipe pipe = Pipe.open();

        // 2.获取sink通道
        Pipe.SinkChannel sinkChannel = pipe.sink();

        // 3.创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("atguigu".getBytes(StandardCharsets.UTF_8));
        byteBuffer.flip();

        // 4.写入数据
        sinkChannel.write(byteBuffer);

        // 5.获取source通道
        Pipe.SourceChannel sourceChannel = pipe.source();

        // 6 创建缓冲区，读取数据
        // ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);

        byteBuffer.flip();

        int length = sourceChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array(), 0, length));

        // 7 关闭通道
        sourceChannel.close();
        sinkChannel.close();
    }
}
