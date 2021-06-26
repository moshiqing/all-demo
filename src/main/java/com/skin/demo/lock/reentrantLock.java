package com.skin.demo.lock;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁
 */
public class reentrantLock implements Runnable {

    private ReentrantLock reentrantLock = new ReentrantLock(true);

    public void get() {
        System.out.println(Thread.currentThread().getName() + "线程进入1");

        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName() + "线程进入2");
        set();

        reentrantLock.unlock();
        System.out.println(Thread.currentThread().getName() + "线程进入4");

    }

    public void set() {
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName() + "线程进入3");

        reentrantLock.unlock();

    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        com.skin.demo.lock.reentrantLock t = new reentrantLock();
        for (int i = 0; i < 10; i++) {
            new Thread(t).start();
        }

    }
}
