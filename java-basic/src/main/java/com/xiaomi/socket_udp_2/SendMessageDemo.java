package com.xiaomi.socket_udp_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SendMessageDemo {

    public static void main(String[] args) throws IOException {
        /*
            按照下面的要求实现程序
                UDP发送数据：数据来自于键盘录入，直到输入的数据是886，发送数据结束
                UDP接收数据：因为接收端不知道发送端什么时候停止发送，故采用死循环接收
         */
        // 1.创建DatagramSocket对象
        DatagramSocket ds = new DatagramSocket();

        // 2.打包数据
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要说的话：");

        while (true) {
            String str = sc.nextLine();
            if ("886".equals(str)) {
                break;
            }
            byte[] bytes = str.getBytes();
            InetAddress address = InetAddress.getByName("localhost");
            int port = 9999;
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length, address, port);
            // 3.发送数据
            ds.send(dp);
        }

        // 4.释放资源
        ds.close();
    }
}
