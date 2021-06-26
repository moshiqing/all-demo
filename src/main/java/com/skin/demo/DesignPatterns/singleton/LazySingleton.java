package com.skin.demo.DesignPatterns.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:懒汉模式
 * @author: moshiqing
 * @time: 2020/3/11 17:55
 */
public class LazySingleton {

    private static volatile LazySingleton lazySingleton = null;

    private LazySingleton() {

    }

    public static synchronized LazySingleton getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
            return lazySingleton;
        }
        return lazySingleton;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 500; i++) {
            executorService.execute(() -> {
                LazySingleton instance = LazySingleton.getInstance();
                System.out.println(instance.toString());
            });
        }
        executorService.shutdown();

    }
}
