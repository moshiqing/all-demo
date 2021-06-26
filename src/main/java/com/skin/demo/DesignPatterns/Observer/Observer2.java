package com.skin.demo.DesignPatterns.Observer;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/3/25 10:05
 */
public class Observer2 implements Observer {
    @Override
    public void update() {
        System.out.println("observer2 has received!");
    }
}
