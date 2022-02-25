package com.atguigu.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ServerSocketChannelDemo {
    public static void main(String[] args) throws Exception {
        // 端口号
        int port = 8888;
        // buffer
        ByteBuffer buffer = ByteBuffer.wrap("hello atguigu".getBytes(StandardCharsets.UTF_8));
        // ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        // 设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 监听是否新的连接传入
        while (true) {
            System.out.println("正在等待连接...");
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel == null) {
                System.out.println("null...没有新的连接传入...");
                Thread.sleep(2000);
            } else {
                System.out.println("Incoming connection from:" + socketChannel.socket().getRemoteSocketAddress());
                // 指针指向0
                buffer.rewind();
                socketChannel.write(buffer);
                socketChannel.close();
            }
        }
    }
}
