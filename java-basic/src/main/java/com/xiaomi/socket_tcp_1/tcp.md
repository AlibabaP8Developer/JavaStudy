# TCP通信程序

TCP通信协议是一种可靠的网络协议，它在通信的两端各建立一个socket对象通信之前要保证连接已经建立
通过socket产生IO流来进行网络通信

## 客户端
1.创建客户端的socket对象与指定服务端连接
    Socket(String host, int port)
2.获取输出流，写数据
    OutputStream getOutputStream()
3.释放资源
    void close()

## 服务端

1.创建服务器端的socket对象（serverSocket）
    ServerSocket(int port)
2.监听客户端连接，返回一个Socket对象
    Socket accept()
3.获取输入流，读数据，并把数据显示在控制台
    
4.释放资源