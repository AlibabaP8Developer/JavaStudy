package com.xiaomi.socket_tcp_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP协议，发送数据
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 1.创建serverSocket对象
        ServerSocket ss = new ServerSocket(10000);
        // 2.监听客户端的连接
        // 等待客户端连接；若无客户端连接，则卡死在ss.accept()
        Socket socket = ss.accept();
        // 3.从连接通道中获取
        //InputStream is = socket.getInputStream();
        // 转换字符流
        //InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        int b;
        while ((b=br.read()) != -1) {
            System.out.print((char)b);
        }
        // 4.释放资源
        socket.close();
        ss.close();
    }
}
