package com.github.lock;

public class LockSyncDemo {

    Object object = new Object();
    public void m1() {
        synchronized (object) {
            System.out.println("---hello synchronized");
        }
    }

    public static void main(String[] args) {

    }
}
