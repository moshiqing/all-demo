package com.skin.demo.DesignPatterns.Adapter;

/**
 * @description: 适配器模式
 * @author: moshiqing
 * @time: 2020/3/24 9:21
 */
public class Adapter extends Source implements Targetable {

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }

    public static void main(String[] args) {
        Targetable targetable = new Adapter();
        targetable.method2();
        targetable.method1();
    }
}
