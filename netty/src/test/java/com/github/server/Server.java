package com.github.server;

import com.github.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class Server {

    public static void main(String[] args) throws IOException {
        // 创建selector，管理多个channel
        Selector selector = Selector.open();
        // 使用nio来理解阻塞模式  单线程
        ByteBuffer buffer = ByteBuffer.allocate(16);

        // 1 创建了服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 非阻塞模式
        ssc.configureBlocking(false);

        // 建立selector 和 channel的联系（注册)
        // SelectionKey 就是将来事件发生后，通过它可以知道事件和哪个channel的事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        // key 只关注accept
        /*
            accept 会在有连接请求时
            connect 是客户端连接建立后触发
            read 可读事件
            write 可写事件
         */
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.debug("register key:{}", sscKey);

        // 2 绑定监听端口
        ssc.bind(new InetSocketAddress(8080));

        while (true) {
            // selector方法,没有事件发生，线程阻塞，有事件，线程才会恢复运行
            selector.select();
            // 处理事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                log.debug("key:{}", key);
                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                SocketChannel sc = channel.accept();
                log.debug("sc:{}", sc);
                key.cancel();
            }
        }
    }
}
