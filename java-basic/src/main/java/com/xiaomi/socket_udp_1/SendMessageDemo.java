package com.xiaomi.socket_udp_1;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class SendMessageDemo {

    public static void main(String[] args) throws IOException {
        // 发送数据

        // 1.创建DatagramSocket对象（快递公司）
        // 细节：
        // 绑定端口  以后我们就是通过这个端口往外发送
        // 空参：所有可用的端口中随机一个进行使用
        // 有参：指定端口号进行绑定
        DatagramSocket ds = new DatagramSocket();
        // 2.打包数据
        // byte buf[]
        // int offset
        // int length
        // SocketAddress address
        String str = "你好啊";
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        InetAddress address = InetAddress.getByName("localhost");
        int port = 8888;
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, address, port);
        // 3.发送数据
        ds.send(dp);
        // 4.释放资源
        ds.close();
    }
}
