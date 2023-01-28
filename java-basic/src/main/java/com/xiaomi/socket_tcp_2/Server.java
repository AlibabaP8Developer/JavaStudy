package com.xiaomi.socket_tcp_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP协议，发送数据
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 客户端：多次发送数据
        // 服务器：接收多次接收数据，并打印
        // 1.创建ServerSocket对象并绑定10000访问
        ServerSocket ss = new ServerSocket(10000);
        // 2.等待客户端连接
        Socket socket = ss.accept();
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        int b;
        while ((b= isr.read())!= -1) {
            System.out.print((char) b);
        }
        // 4.释放资源
        socket.close();
        ss.close();
    }
}
