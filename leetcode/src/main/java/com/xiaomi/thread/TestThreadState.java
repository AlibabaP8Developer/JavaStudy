package com.xiaomi.thread;

public class TestThreadState {

    public static final Object LOCK = new Object();

    public static void main(String[] args) {
        testWaiting();
    }

    private static void testWaiting() {
        Thread t2 = new Thread(() -> {
            synchronized (LOCK) {
                System.out.println("before waiting 1 ... ");
                try {
                    // 3
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        t2.start();

        System.out.println("state 2: " + t2.getState());

        synchronized (LOCK) {
            System.out.println("state 4:" + t2.getState());
            LOCK.notify();// 5
            System.out.println("state 6:" + t2.getState());
        }

        System.out.println("state 7:" + t2.getState());
    }

    private static void testNewRunableTerminated() {
        Thread t1 = new Thread(() -> {
            // 3
            System.out.println("running...");
        }, "t1");

        // 1
        System.out.println("state 1:" + t1.getState());
        t1.start();
        // 2
        System.out.println("state 2:" + t1.getState());
        // 4
        System.out.println("state 4:" + t1.getState());
    }

    public static void testBlocked() {
        Thread t2 = new Thread(() -> {
            System.out.println("before sync 3...");
            synchronized (LOCK) {
                System.out.println("in sync 4...");
            }
        }, "t2");

        t2.start();

        System.out.println("state 1:" + t2.getState());

        synchronized (LOCK) {
            System.out.println("state 2: " + t2.getState());
        }

        System.out.println("state 5:" + t2.getState());
    }
}
