package com.atguigu.chat.client;

public class UserTwoClient {
    public static void main(String[] args) {
        try {
            new ChatClient().startClient("朱元璋");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
