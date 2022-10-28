package com.xiaomi.string;

import java.util.StringJoiner;

public class StringJoinerDemo1 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("emo").append(",").append("woo");
        System.out.println(sb);

        StringJoiner stringJoiner1 = new StringJoiner(",");
        stringJoiner1.add("aaa").add("ccc").add("bbb");
        System.out.println(stringJoiner1);

        StringJoiner stringJoiner = new StringJoiner(",", "[","]");
        stringJoiner.add("hello").add("string").add("joiner");

        System.out.println(stringJoiner);
        System.out.println(stringJoiner.length());
        System.out.println(stringJoiner);
    }
}
