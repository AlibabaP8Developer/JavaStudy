package com.xiaomi.socket_tcp_1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        // 1.创建socket对象
        // 细节：在创建对象的同时会连接服务端
        // 如果连接不上，代码会报错
        Socket socket = new Socket("localhost", 10000);
        // 2.可以从连接通道中获取输出流
        OutputStream os = socket.getOutputStream();
        os.write("你好， 拓跋思恭!".getBytes());
        // 3.释放资源
        os.close();
        socket.close();
    }
}
