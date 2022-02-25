package com.atguigu.chat.client;

public class UserOneClient {
    public static void main(String[] args) {
        try {
            new ChatClient().startClient("嬴政");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
