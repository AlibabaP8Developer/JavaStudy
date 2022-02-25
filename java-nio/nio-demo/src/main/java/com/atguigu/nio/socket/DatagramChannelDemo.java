package com.atguigu.nio.socket;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class DatagramChannelDemo {
    // 发送的实现
    @Test
    public void sendDatagram() throws Exception {
        // 打开DatagramChannel
        DatagramChannel sendChannel = DatagramChannel.open();
        InetSocketAddress sendAddress = new InetSocketAddress("localhost", 9999);
        // 发送
        while (true) {
            ByteBuffer buffer = ByteBuffer.wrap("发送atguigu...".getBytes(StandardCharsets.UTF_8));
            sendChannel.send(buffer, sendAddress);
            System.out.println("已经完成了发送...");
            Thread.sleep(3000);
        }
    }

    // 接收的实现
    @Test
    public void receiveDatagram() throws Exception {
        // 打开DatagramChannel
        DatagramChannel recevieChannel = DatagramChannel.open();
        InetSocketAddress receviceAddress = new InetSocketAddress(9999);
        // 绑定
        recevieChannel.bind(receviceAddress);
        // buffer
        ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
        // 接收
        while (true) {
            receiveBuffer.clear();
            SocketAddress socketAddress = recevieChannel.receive(receiveBuffer);
            receiveBuffer.flip();
            System.out.println(socketAddress.toString());
            System.out.println(Charset.forName("UTF-8").decode(receiveBuffer));
        }
    }

    // 连接 read和write
    @Test
    public void testConnect() throws Exception {
        DatagramChannel conChannel = DatagramChannel.open();
        // 绑定
        conChannel.bind(new InetSocketAddress(9999));
        // 连接
        conChannel.connect(new InetSocketAddress("localhost", 9999));
        // write方法
        conChannel.write(ByteBuffer.wrap("发送atguigu...".getBytes(StandardCharsets.UTF_8)));
        // 创建buffer
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        while (true) {
            readBuffer.clear();
            conChannel.read(readBuffer);
            readBuffer.flip();
            System.out.println(Charset.forName("UTF-8").decode(readBuffer));
        }
    }
}
