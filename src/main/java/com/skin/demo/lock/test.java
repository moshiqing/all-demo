package com.skin.demo.lock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class test {

    static class take implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":::" + UUID.randomUUID().toString().substring(1, 5));
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        ExecutorService e= Executors.newFixedThreadPool(5);
//        for(int i=0;i<500;i++){
//            e.execute(new take());

        Map map = new HashMap(10);
        for (int i = 0; i < 15; i++) {
            map.put(UUID.randomUUID().toString().substring(0, 1), UUID.randomUUID().toString().substring(0, 5));
        }
        System.out.println(map.size());
    }
}
