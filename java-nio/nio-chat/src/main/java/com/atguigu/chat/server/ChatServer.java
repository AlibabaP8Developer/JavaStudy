package com.atguigu.chat.server;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * 服务器端
 */
public class ChatServer {

    /**
     * 服务器端启动的方法
     */
    public void startServer() throws Exception {
        // 1 创建selector选择器
        Selector selector = Selector.open();

        // 2 创建serversocketchannel通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 3 为channel通道绑定监听端口
        ServerSocketChannel bind = serverSocketChannel.bind(new InetSocketAddress(8000));
        // 设置非阻塞模式
        serverSocketChannel.configureBlocking(false);

        // 4 把channel通道注册到selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器已经启动成功了");

        // 5 循环，等待有新的连接接入
        while (true) {
            // 获取channel数量
            int readChannels = selector.select();
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
                // 6-1如果accept状态
                if (selectionKey.isAcceptable()) {
                    acceptOperator(serverSocketChannel, selector);
                }
                // 6-2如果可读状态
                if (selectionKey.isReadable()) {
                    readOperator(selector, selectionKey);
                }
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
            castOtherClient(msg, selector, socketChannel);
        }
    }

    private void castOtherClient(String msg, Selector selector, SocketChannel socketChannel) throws Exception {
        // 1 获取所有已经接入客户端
        Set<SelectionKey> selectionKeySet = selector.keys();
        // 2 循环所有channel广播消息
        for (SelectionKey selectionKey: selectionKeySet) {
            // 获取每个channel
            Channel channel = selectionKey.channel();
            // 不需要给自己发送
            if (channel instanceof SocketChannel && channel != socketChannel) {
                ((SocketChannel) channel).write(Charset.forName("utf-8").encode(msg));
            }
        }
    }

    /**
     * 处理接入状态操作
     *
     * @param serverSocketChannel
     * @param selector
     * @throws Exception
     */
    private void acceptOperator(ServerSocketChannel serverSocketChannel, Selector selector) throws Exception {
        // 1 接入状态，创建socketchannel
        SocketChannel socketChannel = serverSocketChannel.accept();

        // 2 把socketchannel设置非阻塞模式
        socketChannel.configureBlocking(false);

        // 3 把channel注册到selector选择器上，监听可读状态
        socketChannel.register(selector, SelectionKey.OP_READ);

        // 4 客户端回复信息
        socketChannel.write(Charset.forName("UTF-8").encode("欢迎您已进入聊天室，请注意隐私安全！"));
    }

    /**
     * 启动主方法
     */
    public static void main(String[] args) {
        try {
            new ChatServer().startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
