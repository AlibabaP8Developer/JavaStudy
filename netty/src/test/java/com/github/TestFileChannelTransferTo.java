package com.github;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TestFileChannelTransferTo {
    public static void main(String[] args) {
        try {
            FileChannel from = new FileInputStream("netty/data.txt").getChannel();
            FileChannel to = new FileOutputStream("netty/to.txt").getChannel();
            //from.transferTo(0, from.size(), to);
            long size = from.size();
            for (long left = size; left > 0; ) {
                System.out.println("postion:" + (size - left) + "left:" + left);
                left -= from.transferTo(size - left, left, to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
