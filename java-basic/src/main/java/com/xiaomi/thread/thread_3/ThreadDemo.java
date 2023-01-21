package com.xiaomi.thread.thread_3;

import java.util.concurrent.FutureTask;

public class ThreadDemo {
    public static void main(String[] args) throws Exception {
        /*
            多线程第三种方式
            特点：可以获取到多线程运行的结果
            1.创建一个类MyCallable实现Callable接口
            2.重写call（有返回值）
            3.创建MyCallable的对象（表示多线程要执行的任务）
            4.创建FutureTask的对象（作用管理多线程运行的结果）
            5.创建Thread类的对象，并启动线程
         */

        MyCallable mc = new MyCallable();

        FutureTask<Integer> futureTask = new FutureTask<>(mc);

        Thread thread1 = new Thread(futureTask);

        thread1.start();

        Integer result = futureTask.get();
        System.out.println(result);
    }
}
