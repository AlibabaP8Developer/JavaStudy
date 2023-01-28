package com.xiaomi.socket_tcp_3;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        // 客户端：多次发送数据
        // 服务器：接收多次接收数据，并打印
        // 1.创建Socket对象并连接访问端
        Socket socket = new Socket("localhost", 10000);
        // 2.写出数据
        String str = "完颜亮";
        OutputStream os = socket.getOutputStream();
        os.write(str.getBytes());

        // 结束标记
        socket.shutdownOutput();

        // 接收服务端回写的数据
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        int b;
        while ((b = isr.read()) != -1) {
            System.out.print((char) b);
        }

        // 3.释放资源
        socket.close();
    }
}
