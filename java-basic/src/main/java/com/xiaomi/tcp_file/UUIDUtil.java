package com.xiaomi.tcp_file;

import java.util.UUID;

public class UUIDUtil {

    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(UUIDUtil.getId());
    }
}
