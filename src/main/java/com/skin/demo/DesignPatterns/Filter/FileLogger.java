package com.skin.demo.DesignPatterns.Filter;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/4/10 15:57
 */
public class FileLogger extends AbstractFilter {

    FileLogger(int level) {
        this.level = level;
    }

    @Override
    public void doFilter(int level, String message) {
        System.out.println("File::::" + level + ":---=" + message);
    }
}
