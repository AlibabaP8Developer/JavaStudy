package com.xiaomi.face;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BigDecimalDemo {

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("4.2");
        BigDecimal d = new BigDecimal("2.1");

        BigDecimal x = a.subtract(b);
        BigDecimal y = b.subtract(c);

        System.out.println(x);
        System.out.println(y);

        float r = 1.0f - 0.9f;
        float t = 0.9f - 0.8f;
        System.out.println(r);// 0.100000024
        System.out.println(t);// 0.099999964
        System.out.println(r == t);// false

        /*
        加减乘除
            add 方法用于将两个 BigDecimal 对象相加，
            subtract 方法用于将两个 BigDecimal 对象相减。
            multiply 方法用于将两个 BigDecimal 对象相乘，
            divide 方法用于将两个 BigDecimal 对象相除。

            这里需要注意的是，在我们使用 divide 方法的时候尽量使用 3 个参数版本，
            并且RoundingMode 不要选择 UNNECESSARY，
            否则很可能会遇到 ArithmeticException（无法除尽出现无限循环小数的时候），
            其中 scale 表示要保留几位小数，roundingMode 代表保留规则。
            public BigDecimal divide(BigDecimal divisor, int scale, RoundingMode roundingMode) {
                return divide(divisor, scale, roundingMode.oldMode);
            }
         */

        BigDecimal add = a.add(b);
        System.out.println("add:" + add);
        BigDecimal divide = c.divide(d, 2, RoundingMode.CEILING);
        System.out.println("divide:" + divide);

        /**
         * 大小比较
         * a.compareTo(b) : 返回 -1 表示 a 小于 b，0 表示 a 等于 b ， 1 表示 a 大于 b
         */
        BigDecimal a1 = new BigDecimal("10.0");
        BigDecimal b1 = new BigDecimal("20.9");
        // 1
        System.out.println(a1.compareTo(b1));

        /**
         * 保留几位小数
         * 通过 setScale方法设置保留几位小数以及保留规则。保留规则有挺多种，不需要记，IDEA 会提示。
         */
        BigDecimal m = new BigDecimal("1.255433");
        BigDecimal n = m.setScale(2,RoundingMode.HALF_DOWN);
        // 1.255
        System.out.println(n);

        double dou = 20.9;
        double dou2 = 21.2;
        // 42.099999999999994
        System.out.println(dou+dou2);
        BigDecimal bigDecimal1 = BigDecimal.valueOf(dou);
        BigDecimal bigDecimal2 = BigDecimal.valueOf(dou2);
        // 42.1
        System.out.println(bigDecimal1.add(bigDecimal2).doubleValue());
    }
}
