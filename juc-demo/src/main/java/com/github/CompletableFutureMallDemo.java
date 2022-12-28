package com.github;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 链式调用
 * 电商比价
 */
public class CompletableFutureMallDemo {

    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("tb"),
            new NetMall("pdd"),
            new NetMall("tmall"),
            new NetMall("dd")
    );

    public static List<String> getPrice(List<NetMall> list, String productName) {
        List<String> collect = list.stream().map(netMall -> {
            return String.format(productName + "in %s price is %.2f", netMall.getMallName(),
                    netMall.calcPrice(productName));
        }).collect(Collectors.toList());
        return collect;
    }

    public static List<String> getPriceCompletableFuture(List<NetMall> list, String productName) {
        return list.stream().map(netMall -> {
                    return CompletableFuture.supplyAsync(() -> {
                        String format = String.format(productName + "in %s price is %.2f", netMall.getMallName(),
                                netMall.calcPrice(productName));
                        return format;
                    });
                }).collect(Collectors.toList())
                .stream().map(s -> s.join()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        //Student student = new Student();
        //student.setName("11").setAge("12").setAddress("bj").setId("10");

        /*CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "123456";
        });

        try {
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // get和join
        System.out.println(future.join());*/

        //System.out.println(ThreadLocalRandom.current().nextDouble() * 2 + "mysql".charAt(0));

        long start = System.currentTimeMillis();

        List<String> mysql = getPrice(list, "mysql");
        for (String list : mysql) {
            System.out.println(list);
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");

        System.out.println("=======");

        long start2 = System.currentTimeMillis();
        List<String> mysql2 = getPriceCompletableFuture(list, "mysql");
        for (String list : mysql2) {
            System.out.println(list);
        }
        long end2 = System.currentTimeMillis();
        System.out.println((end2 - start2) + "ms");

    }
}

@Data
// 链式调用
@Accessors(chain = true)
class NetMall {
    private String mallName;

    public NetMall(String mallName) {
        this.mallName = mallName;
    }

    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }

}