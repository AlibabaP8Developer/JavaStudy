package com.atguigu.chat.client;

public class UserThreeClient {
    public static void main(String[] args) {
        try {
            new ChatClient().startClient("爱新觉罗 玄烨");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
