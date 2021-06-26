package com.skin.demo.ComletableFuture;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/4/29 10:04
 */
public class Demo {

    public static void main(String[] args) throws Exception {
        boss boss = new boss("老板");
        FutureRunnablePromise staff1 = new staff("员工 张三");
        staff1.getCallbackWork(() -> System.out.println("张三 干完了开始吹水"));
        FutureRunnablePromise staff2 = new staff("员工 李四");
        staff2.getCallbackWork(() -> System.out.println("李四 干完了开始吹水"));
        Thread thread = new Thread(boss);
        Thread thread1 = new Thread(staff1);
        Thread thread2 = new Thread(staff2);
        boss.setStafflist(staff1);
        boss.setStafflist(staff2);
        thread.start();
        thread1.start();
        thread2.start();

    }

    /**
     * 工作的间隙
     */
    public static void chuishui() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
