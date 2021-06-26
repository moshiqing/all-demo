package com.skin.demo.error;


import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.*;

class resouce {
    private volatile boolean flag = true;
    private BlockingDeque blockingDeque;

    public resouce(BlockingDeque blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    public void putBD() throws Exception {
        while (flag) {
            String str = UUID.randomUUID().toString().substring(0, 5);
            blockingDeque.offer(str, 5L, TimeUnit.SECONDS);
            System.out.println("生成数据:" + str);
            TimeUnit.SECONDS.sleep(2);

        }
    }

    public void getBD() throws Exception {
        while (flag) {
            Object o = blockingDeque.poll(5L, TimeUnit.SECONDS);
            if (o != null) {
                System.out.println("取出数据" + o.toString());
                TimeUnit.SECONDS.sleep(3);
            }
        }

    }

}

public class demo {

    public static void main(String[] args) throws Exception {

        resouce r = new resouce(new LinkedBlockingDeque(10));

        new Thread(() -> {
            try {
                r.putBD();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AAAA").start();

        new Thread(() -> {
            try {
                r.getBD();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BBB").start();

    }
}
