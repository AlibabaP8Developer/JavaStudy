package com.xiaomi.socket_udp_1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReceiveMessageDemo {
    public static void main(String[] args) throws IOException {
        // 接收数据

        // 1.创建DatagramSocket对象（快递公司）
        // 在接收的时候，一定要绑定端口
        // 而且绑定的端口一定要跟发送的端口保持一致
        DatagramSocket ds = new DatagramSocket(8888);
        // 2.接收数据包
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        ds.receive(dp);

        // 3.解析数据包
        byte[] data = dp.getData();
        int length = dp.getLength();
        InetAddress address = dp.getAddress();
        int port = dp.getPort();
        System.out.println("接收到的数据：" + new String(data, 0, length));
        System.out.println("该数据是从：" + address + " 这台电脑上端口：" + port);
    }
}
