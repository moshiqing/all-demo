package com.skin.demo.DesignPatterns.Filter;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/4/10 14:43
 */
public class ConsoleLogger extends AbstractFilter {

    ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    public void doFilter(int level, String message) {
        System.out.println("Console::::" + level + ":---=" + message);
    }
}
