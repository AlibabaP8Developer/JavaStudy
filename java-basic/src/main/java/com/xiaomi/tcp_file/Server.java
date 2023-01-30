package com.xiaomi.tcp_file;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP协议，发送数据
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 客户端：将本地文件上传到服务器，接收服务器的反馈
        // 服务器：接收客户端上传的文件，上传完毕之后给出反馈

        // 1.创建ServerSocket对象并绑定10000访问
        ServerSocket ss = new ServerSocket(10000);
        // 2.等待客户端连接
        System.out.println("等待客户端连接");
        Socket socket = ss.accept();

        System.out.println("回写成功");
        // 3.读取数据并保存到本地文件
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("/Users/lizhenghang/workspace/java/JavaStudy/java-basic/server_dir/" + UUIDUtil.getId() + ".jpeg"));

        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }

        // 4.回写数据
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write("upload success!");

        // 5.释放资源
        socket.close();
        ss.close();
    }
}
