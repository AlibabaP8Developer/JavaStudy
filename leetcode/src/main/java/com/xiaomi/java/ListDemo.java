package com.xiaomi.java;

import java.util.List;

public class ListDemo<T> {

    private T key;

    public List<?> sayHello() {

        return null;
    }

    public T merge() {
        return null;
    }

    public static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }

    public static void main(String[] args) {

    }
}
