package com.github.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        // 连接客户端
        socketChannel.connect(new InetSocketAddress("localhost", 8080));
        System.out.println("waiting...");
        socketChannel.write(Charset.defaultCharset().encode("hi!"));
    }
}
