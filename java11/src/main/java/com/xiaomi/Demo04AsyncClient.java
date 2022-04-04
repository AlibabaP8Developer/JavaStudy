package com.xiaomi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class Demo04AsyncClient {
    /**
     * 发起异步请求
     * @param args
     */
    public static void main(String[] args) {
        var url = "https://www.youtube.com/watch?v=05Z5CcH-k94&ab_channel=%E8%80%81%E6%A2%81";
        // 1.创建一个httpclient客户端对象
        HttpClient httpClient = HttpClient.newHttpClient();
        // 2.创建一个请求对象:request，封装地址，参数，请求方式
        // get方法调用可以省略，默认就是get请求
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)).GET()
                .build();
        // 3.通过客户端对象发起request的异步请求
        CompletableFuture<HttpResponse<String>> future = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        // 4.异步监听结果数据
        future.whenComplete(new BiConsumer<HttpResponse<String>, Throwable>() {
            @Override
            public void accept(HttpResponse<String> stringHttpResponse, Throwable throwable) {
                // 5.结果数据处理
                if (throwable != null) {
                    // 请求处理
                    throwable.printStackTrace();
                } else {
                    var code = stringHttpResponse.statusCode();
                    var result = stringHttpResponse.body();
                    System.out.println(code + "   :    " + result);
                }
            }
        })
                .join();// 阻塞等待异步结果

        System.out.println("结束!!!");
    }
}
