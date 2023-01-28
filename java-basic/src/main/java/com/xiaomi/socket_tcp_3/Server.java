package com.xiaomi.socket_tcp_3;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
        System.out.println("等待客户端连接");
        Socket socket = ss.accept();
        System.out.println("客户端已经连接");
        // 3.socket中获取输入流读取数据
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        int b;
        System.out.println("准备读取客户端发送过来的数据");
        // read方法会从连接通道中读取数据
        // 但是需要一个结束标记，此处的循环才会停止
        // 否则，程序会一直停在read方法这里，等待读取下面的数据
        while ((b = isr.read()) != -1) {
            System.out.println("正在读取");
            System.out.println((char) b);
        }
        System.out.println("数据读取完毕，准备回写");
        // 回写数据
        var str = "收到数据了...";
        OutputStream os = socket.getOutputStream();
        os.write(str.getBytes());
        System.out.println("回写成功");
        // 4.释放资源
        socket.close();
        ss.close();
    }
}
