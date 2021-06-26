package com.skin.demo.DesignPatterns.Filter;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/4/10 14:31
 */
public abstract class AbstractFilter {

    protected int level = 1;

    protected AbstractFilter nextFilter;

    /**
     * 下一个元素
     *
     * @param nextFilter
     */
    public void setNextFilter(AbstractFilter nextFilter) {
        this.nextFilter = nextFilter;
    }

    /**
     * 责任链的传递
     *
     * @param level
     * @param message
     */
    public void filter(int level, String message) {
        if (this.level == level) {
            doFilter(level, message);
        }
        if (nextFilter != null) {
            nextFilter.filter(level, message);
        }
    }

    /**
     * 具体拦截逻辑
     */
    public abstract void doFilter(int level, String message);
}
