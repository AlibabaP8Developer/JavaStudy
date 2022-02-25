package com.atguigu.nio.buffer;

import org.junit.Test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * @author atguigu
 */
public class BufferDemo1Test {

    @Test
    public void buffer01() throws Exception {
        // FileChannel
        RandomAccessFile file = new RandomAccessFile("/Users/lizhenghang/Desktop/01-demo.txt", "rw");
        FileChannel channel = file.getChannel();
        // 创建buffer，指定大小，创建buffer缓冲区，大小是1024
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 读 bytesRead = -1读到了文件末尾
        int bytesRead = channel.read(buffer);
        while (bytesRead != -1) {
            // read模式 buffer->read模式
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();
            bytesRead = channel.read(buffer);
        }
        file.close();
    }

    @Test
    public void buffer02() throws Exception {
        IntBuffer buffer = IntBuffer.allocate(8);
        // buffer放 buffer.capacity()：buffer大小
        for (int i = 0; i < buffer.capacity(); i++) {
            int j = 2 * (i + 1);
            buffer.put(j);
        }
        // 重置缓冲区
        buffer.flip();
        // 获取
        while (buffer.hasRemaining()) {
            int value = buffer.get();
            System.out.println(value + "");
        }
    }
}
