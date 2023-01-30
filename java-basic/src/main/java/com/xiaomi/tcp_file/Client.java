package com.xiaomi.tcp_file;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        // 客户端：将本地文件上传到服务器，接收服务器的反馈
        // 服务器：接收客户端上传的文件，上传完毕之后给出反馈

        // 1.创建Socket对象并连接服务器
        Socket socket = new Socket("localhost", 10000);
        // 2.写出数据
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("/Users/lizhenghang/workspace/java/JavaStudy/java-basic/client_dir/jjj.jpeg"));
        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        // 往服务器写出结束标记
        socket.shutdownOutput();

        // 3.接收服务器的回写数据
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = br.readLine();

        // 4.释放资源
        socket.close();
    }
}
