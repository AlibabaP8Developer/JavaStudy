package com.itheima.pattern.decorator;

import java.math.BigDecimal;

/**
 * 炒面
 */
public class FriedNoodles extends FastFood {
    public FriedNoodles() {
        super(12, "炒面");
    }

    @Override
    public float cost() {
        return getPrice();
    }
}
