package com.atguigu.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo {

    public static void main(String[] args) throws Exception {
        // 创建SocketChannel
        // 方式一
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));
        // 方式二
//        SocketChannel socketChannel2 = SocketChannel.open();
//        socketChannel2.connect(new InetSocketAddress("www.baidu.com", 80));

        // 设置阻塞和非阻塞模式
        socketChannel.configureBlocking(false);

        // 读操作
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        socketChannel.read(byteBuffer);
        socketChannel.close();
        System.out.println("read over");
    }
}
