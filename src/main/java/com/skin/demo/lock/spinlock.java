package com.skin.demo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class spinlock implements Runnable {

    AtomicReference<Thread> a = new AtomicReference();


    public void lock() {
        Thread t = Thread.currentThread();
        System.out.println(t.getName() + "尝试去获得锁");
        while (!a.compareAndSet(null, t)) {

        }
        System.out.println(t.getName() + "获取到当前的锁");
    }

    public void unlock() {
        Thread t = Thread.currentThread();
        a.compareAndSet(t, null);
        System.out.println(t.getName() + "解锁成功");
    }

    @Override
    public void run() {

        try {
            lock();
            unlock();
            TimeUnit.SECONDS.sleep(2);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        spinlock s = new spinlock();
        for (int i = 0; i < 10; i++) {
            new Thread(s).start();

        }
    }

}
