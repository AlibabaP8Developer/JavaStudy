package com.atguigu.chat.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * 客户端
 */
public class ChatClient {

    /**
     * 启动客户端方法
     */
    public void startClient(String name) throws Exception {
        // 连接服务器端
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8000));
        // 接收服务器端响应端数据
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        // 创建线程
        new Thread(new ClientThread(selector)).start();

        // 向服务器端发送消息
        Scanner scanner = new Scanner(System.in);
        System.out.println("现在可以开始聊天了...");
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            if (msg.length() > 0) {
                socketChannel.write(Charset.forName("utf-8").encode(name + " : " + msg));
            }
        }
    }
}
