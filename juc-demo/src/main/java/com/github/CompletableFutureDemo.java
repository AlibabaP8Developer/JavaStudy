package com.github;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CompletableFutureDemo {

    /*
        三个特点：多线程、有返回、异步任务
        FutureTask(Callable<V> callable)
        Future
            优点：Future线程池异步多线程任务配合，能显著提高程序的执行效率
            缺点：get方法求结果，如果计算没有完成容易导致程序阻塞
     */
    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<String>(new MyThread2());
        // 异步任务
        Thread thread1 = new Thread(futureTask, "t1");
        thread1.start();

        try {
            System.out.println(futureTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyThread2 implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("come in call()...");
        return "hello callable";
    }
}
