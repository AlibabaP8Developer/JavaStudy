package com.xiaomi.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// 异步和同步调用
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 同步
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ": completableFuture1...");
        });
        completableFuture1.get();

        System.out.println("=========");

        // mq消息队列
        // 异步调用
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ": completableFuture2...");
            return 1024/0;
        });
        completableFuture2.whenComplete((t, u)->{
            System.out.println("----t=" + t);
            System.out.println("----u=" + u);
        }).get();
    }
}
