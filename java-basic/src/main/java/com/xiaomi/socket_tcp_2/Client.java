package com.xiaomi.socket_tcp_2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        // 客户端：多次发送数据
        // 服务器：接收多次接收数据，并打印
        // 1.创建Socket对象并连接访问端
        Socket socket = new Socket("localhost", 10000);
        // 2.写出数据
        Scanner sc = new Scanner(System.in);
        OutputStream os = socket.getOutputStream();

        while (true) {
            System.out.println("请熟人您要发送的信息：");
            String str = sc.nextLine();
            if ("886".equals(str)) {
                break;
            }
            os.write(str.getBytes());
        }
        // 3.释放资源
        socket.close();
    }
}
