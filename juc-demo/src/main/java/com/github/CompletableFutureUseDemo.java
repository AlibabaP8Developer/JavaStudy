package com.github;

import java.util.concurrent.*;

public class CompletableFutureUseDemo {

    public static void main(String[] args) {
        /*
            CompletableFuture优点：
            1.异步任务结束时，会自动回调某个对象的方法
            2.主线程设置好回调后，不再关心异步任务的执行，异步任务之间可以顺序执行
            3.异步任务出错时，会自动回调某个对象的方法
         */
        ExecutorService pool = Executors.newFixedThreadPool(3);

        try {
            CompletableFuture<Integer> async = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "---come in");
                int result = ThreadLocalRandom.current().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (result >= 0) {
                    int i = 10 / 0;
                }
                return result;
            }, pool).whenComplete((v, e) -> { // (v,e) v: 上一步的result值，e：异常
                if (e == null) {
                    System.out.println("计算完成，更新系统updateva：" + v);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("异常情况：" + e.getCause() + "\t" + e.getMessage());
                return null;
            });

            System.out.println(Thread.currentThread().getName() + "：其他任务...");

            // 原因分析：main是用户线程，异步是守护线程，main结束 异步自动关闭
            try {
                // 主线程停3s，不让主线程快结束
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }

    private static void future() {
        CompletableFuture<Object> async = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("after 1s: " + result);
            return result;
        });

        System.out.println(Thread.currentThread().getName() + "其他任务...");

        try {
            System.out.println(async.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
