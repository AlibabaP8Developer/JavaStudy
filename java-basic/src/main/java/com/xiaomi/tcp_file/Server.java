package com.xiaomi.tcp_file;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TCP协议，发送数据
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 客户端：将本地文件上传到服务器，接收服务器的反馈
        // 服务器：接收客户端上传的文件，上传完毕之后给出反馈

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3,  // 核心线程数量，能小于0
                16, // 最大线程数量，不能小于0，最大数量>=核心线程数量
                60, // 空闲线程最大存活时间
                TimeUnit.SECONDS, //时间单位
                new ArrayBlockingQueue<>(2), // 任务队列
                Executors.defaultThreadFactory(), // 创建线程工厂
                new ThreadPoolExecutor.AbortPolicy() // 任务的拒绝策略  阻塞队列
        );

        // 1.创建ServerSocket对象并绑定10000访问
        ServerSocket ss = new ServerSocket(10000);
        // 2.等待客户端连接
        System.out.println("等待客户端连接");
        while (true) {
            Socket socket = ss.accept();
            // 开启一条线程
            // 一个用户就对应服务端的一条线程
            //new Thread(new MyRunnable(socket)).start();
            pool.submit(new MyRunnable(socket));
        }
        //ss.close();
    }
}
