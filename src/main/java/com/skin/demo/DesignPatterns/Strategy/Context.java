package com.skin.demo.DesignPatterns.Strategy;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/4/8 17:43
 */
public class Context {

    private Strategy strategy;

    Context(Strategy strategy) {
        this.strategy = strategy;
    }

    void contextMethod() {
        strategy.dosomthing();
    }


}


