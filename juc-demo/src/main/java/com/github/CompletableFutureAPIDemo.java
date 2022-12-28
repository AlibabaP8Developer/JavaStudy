package com.github;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture常用方法
 * 1.获得结果和触发计算
 * 2.对计算结果进行处理
 * 3.对计算结果进行消费
 * 4.对计算速度进行选用
 * 5.对计算结果进行合并
 * <p>
 * 获取结果
 * <p>
 * public T get()
 * public T join()
 * public T get(long timeout, TimeUnit unit)
 * public T getNow(T valueIfAbsent)
 * 1.没有计算完成的情况下，给我一个替代结果
 * 2.立即获取结果不阻塞
 * 计算完，返回计算完成后的结果
 * 没有计算完，返回设定的valueIfAbsent值
 * 主动触发计算
 * public boolean complete(T value) 是否打断get方法立即返回括号值
 * 对计算结果进行处理
 * thenApply: 计算结果存在依赖关系，这两个线程串行化
 * handle: 计算结果存在依赖关系，这两个线程串行化
 */
public class CompletableFutureAPIDemo {
    public static void main(String[] args) {
    /*    CompletableFuture<Void> accept = CompletableFuture.supplyAsync(() -> {
            return 1;
        }).thenApply(f -> {
            return f + 2;
        }).thenApply(f -> {
            return f + 3;
        }).thenAccept(System.out::println);*/
        //        .thenAccept(r -> {
        //    System.out.println(r);
        //});
        /*
            thenRun: 任务a执行完执行b，并且b不需要a的结果
            thenAccept: 任务a执行完执行b，b需要a的结果，但是任务b无返回值
            thenApply: 任务a执行完执行b，b需要a的结果，同时任务b有返回值
         */
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {
        }).join());
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept((r) -> System.out.println(r)).join());

        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply((r) -> r + "resultB").join());
    }

    public static void main2(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1");
            return 1;
        }, pool).handle((f, e) -> {
            //var i = 10/0;
            System.out.println("2");
            return f + 2;
        }).handle((f, e) -> {
            System.out.println("3");
            return f + 3;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("计算结果：" + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });

        //try {
        //    TimeUnit.SECONDS.sleep(1);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        System.out.println(Thread.currentThread().getName() + "---执行其他");

        // 关闭线程池
        pool.shutdown();
    }

    public static void main1(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });
        //System.out.println(future.get());
        //System.out.println(future.join());
        //System.out.println(future.get(2, TimeUnit.SECONDS));
        // getNow("xxx")备胎值，不想等直接返回
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(future.getNow("xxx"));

        System.out.println(future.complete("completeValue") + "\t" + future.join());
    }
}
