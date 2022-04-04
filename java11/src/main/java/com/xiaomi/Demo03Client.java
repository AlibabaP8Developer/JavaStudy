package com.xiaomi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Demo03Client {
    public static void main(String[] args) {
        var url = "https://www.youtube.com/watch?v=05Z5CcH-k94&ab_channel=%E8%80%81%E6%A2%81";
        // 1.创建一个httpclient客户端对象
        HttpClient httpClient = HttpClient.newHttpClient();
        // 2.创建一个请求对象:request，封装地址，参数，请求方式
        // get方法调用可以省略，默认就是get请求
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)).GET()
                .build();
        // 3.通过httpclient对象发起request请求得到一个响应结果对象
        try {
            // 参数一：请求对象
            // 参数二：响应结果处理成字符串结果
            // 返回的是一个响应对象
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            // 4.得到响应码
            var code = response.statusCode();
            var resp = response.body();
            System.out.println("code:   "+code + "   \nresp:   " + resp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
