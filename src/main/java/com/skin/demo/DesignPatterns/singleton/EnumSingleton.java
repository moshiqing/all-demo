package com.skin.demo.DesignPatterns.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/3/23 9:42
 */
public enum EnumSingleton {

    INSTANCE;
    private int i = 0;

    EnumSingleton() {

    }

    public void doSomething() {
        System.out.println("你要做点什么" + ++i);
    }

    public static void main(String[] args) {


        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int b = 0; b < 100; b++) {
            executorService.execute(() -> {
                EnumSingleton.INSTANCE.doSomething();
            });
        }
        executorService.shutdown();
    }
}
