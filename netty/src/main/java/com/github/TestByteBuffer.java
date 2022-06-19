package com.github;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class TestByteBuffer {

    public static void main(String[] args) {
        // FileChannel
        // 1.输入输出流
        // 2.RandomAccessFile
        try {
            FileChannel channel = new FileInputStream("netty/data.txt").getChannel();
            // 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);

            while (true) {
                // 从channel读取数据，向buffer写入
                int len = channel.read(buffer);
                log.debug("读取到的字节数：{}", len);
                // len==-1 说明读取完毕
                if (len == -1) {
                    break;
                }
                // 打印buffer内容
                buffer.flip();// 切换到读模式
                // 判断是否还有剩余的数据
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    System.out.println((char) b);
                    log.debug("实际的字节：{}", (char) b);
                }
                // 读取完一次后，将buffer切换为写模式
               buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
