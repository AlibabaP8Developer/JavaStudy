package com.atguigu.nio.buffer;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author atguigu
 */
public class BufferDemo2Test {

    static private final int start = 0;
    static private final int size = 1024;

    /**
     * 内存映射文件io
     *
     * @throws Exception
     */
    @Test
    public void buffer04() throws Exception {
        RandomAccessFile raf = new RandomAccessFile("/Users/lizhenghang/Desktop/01-demo.txt" , "rw");
        FileChannel fc = raf.getChannel();
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, start, size);
        mbb.put(0, (byte) 97);
        mbb.put(1023, (byte) 122);
        raf.close();
    }

    /**
     * 直接缓冲区
     */
    @Test
    public void buffer03() throws Exception {
        String infile = "/Users/lizhenghang/Desktop/01-demo.txt";
        FileInputStream fin = new FileInputStream(infile);
        FileChannel finChannel = fin.getChannel();

        String outfile = "/Users/lizhenghang/Desktop/02-demo.txt";
        FileOutputStream fout = new FileOutputStream(outfile);
        FileChannel foutChannel = fout.getChannel();

        // 创建直接缓冲区
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true) {
            buffer.clear();
            int read = finChannel.read(buffer);
            if (read == -1) {
                break;
            }
            buffer.flip();
            foutChannel.write(buffer);
        }
    }

    /**
     * 只读缓冲区
     */
    @Test
    public void buffer02() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        // 创建只读缓冲区
        ByteBuffer readOnly = buffer.asReadOnlyBuffer();
        for (int i = 0; i < buffer.capacity(); i++) {
            byte b = buffer.get(i);
            b *= 10;
            buffer.put(i, b);
        }
        readOnly.position(0);
        readOnly.limit(buffer.capacity());
        while (readOnly.remaining() > 0) {
            System.out.println(readOnly.get());
        }
    }

    /**
     * 缓冲区分片
     *
     * @throws Exception
     */
    @Test
    public void buffer01() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        // buffer.capacity() 获取大小
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        // 创建子缓冲区
        buffer.position(3);
        buffer.limit(7);
        ByteBuffer slice = buffer.slice();
        // 改变子缓冲区内容
        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 10;
            slice.put(i, b);
        }
        buffer.position(0);
        buffer.limit(buffer.capacity());
        while (buffer.remaining() > 0) {
            System.out.println(buffer.get());
        }
    }

}
