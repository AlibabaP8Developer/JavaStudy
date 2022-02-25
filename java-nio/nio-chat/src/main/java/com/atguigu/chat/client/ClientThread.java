package com.atguigu.chat.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class ClientThread implements Runnable {

    private Selector selector;
    public ClientThread(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        while (true) {
            // 获取channel数量
            int readChannels = 0;
            try {
                readChannels = selector.select();
                if (readChannels == 0) {
                    continue;
                }
                // 获取可用的channel
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                // 遍历集合
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    // 移除set集合当前selectionKey
                    iterator.remove();
                    // 6 根据就绪状态，调用对应方法实现具体业务操作
                    // 6-2如果可读状态
                    if (selectionKey.isReadable()) {
                        readOperator(selector, selectionKey);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 处理可读状态操作
     *
     * @param selector
     * @param selectionKey
     */
    private void readOperator(Selector selector, SelectionKey selectionKey) throws Exception {
        // 1 从selectionKey获取到以及就绪的通道
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        // 2 创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 3 循环读取客户端消息
        int readLength = socketChannel.read(byteBuffer);
        String msg = "";
        if (readLength > 0) {
            // 切换读模式
            byteBuffer.flip();
            // 读取内容
            msg += Charset.forName("utf-8").decode(byteBuffer);
        }
        // 4 将channel再次注册到选择器上，监听可读状态
        socketChannel.register(selector, SelectionKey.OP_READ);
        // 5 把客户端发送消息，广播到其他客户端
        if (msg.length() > 0) {
            System.out.println(msg);
        }
    }

}
