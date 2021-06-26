package com.skin.demo.DesignPatterns.Strategy;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/4/8 17:47
 */
public class AStrategy implements Strategy {
    @Override
    public void dosomthing() {
        System.out.println("AAA 现在要去做点什么");
    }
}
