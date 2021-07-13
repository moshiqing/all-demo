package com.skin.demo.thread;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch:位于java.util.concurrent包下，利用它可以实现类似计数器的功能。
 *
 * 应用场景:比如有一个任务C，它要等待其他任务A,B执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
 */
public class ThreadCountDownLatchDemo {

    /**
     * 用于判断线程一是否执行，倒计时设置为1，执行后减1
     */
    private static CountDownLatch c1 = new CountDownLatch (1);

    /**
     * 用于判断线程二是否执行，倒计时设置为1，执行后减1
     */
    private static CountDownLatch  c2 = new CountDownLatch (1);

    public static void main(String[] args) {
        new Thread(()-> {
                System.out.println("产品经理规划需求");
                c1.countDown();
        }).start();

        new Thread(()->{
                try {
                    c1.await();
                    System.out.println("程序员进行开发");
                    c2.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }).start();

        new Thread(()->{
            try {
                c2.await();
                System.out.println("测试产品");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
