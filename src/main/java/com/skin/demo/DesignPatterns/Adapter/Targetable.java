package com.skin.demo.DesignPatterns.Adapter;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/3/24 9:20
 */
public interface Targetable {

    /**
     * 与原类中的方法相同
     */
    void method1();

    /**
     * 新类的方法
     */
    void method2();
}
