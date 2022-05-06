package com.github.utils;

public interface ILock {

    /**
     * 尝试获取锁
     * 	添加锁，nx是互斥，ex是设置超时时间
     * 	set lock th1 ex 10 nx
     * @param timeoutSec 锁持有的超时时间，过期后自动释放
     * @return true代表获取锁成功，false获取锁失败
     */
    boolean tryLock(long timeoutSec);

    /**
     * 释放锁
     * 	del th1
     */
    void unlock();
}
