package com.skin.demo.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier(回环栅栏):通过它可以实现让一组线程等待至某个状态之后再全部同时执行。叫做回环是因为当所有等待线程都被释放以后，
 * CyclicBarrier可以被重用。我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了。
 *
 * 应用场景:公司组织春游,等待所有的员工到达集合地点才能出发，每个人到达后进入barrier状态。都到达后，唤起大家一起出发去旅行
 */
public class CyclicBarrierDemo {
    /**
     * 定义同事类
     */
    private static class Colleague extends Thread{

        // 要约定栅栏；所有的同事实现类都要被此栅栏约束
        private CyclicBarrier barrier;

        Colleague(CyclicBarrier barrier, String name){
            super(name);
            this.barrier = barrier;
        }
        @Override
        public void run() {
            try {
                System.out.println("同事：" + getName() + " 已经到达约定地点,等待其他人");
                barrier.await();
                System.out.println("同事：" + getName()+"人齐了，去吃饭");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int colleagueNum = 5;

        // 1. 定义一个栅栏：5条线程到位之后执行run方法（由最后到达的线程执行）
        CyclicBarrier barrier = new CyclicBarrier(colleagueNum,()->{
                System.out.println("执行此操作的线程：" + Thread.currentThread().getName() + "。所有人均已到达约定地点；领头进门....");
        });

        // 2. 将此栅栏约束在线程上（如果i<其他值）
        for (int i = 0; i < colleagueNum; i++) {
            new Colleague(barrier,"同事" + i).start();
        }


    }
}
