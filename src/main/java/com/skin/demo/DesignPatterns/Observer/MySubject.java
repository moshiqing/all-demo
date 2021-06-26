package com.skin.demo.DesignPatterns.Observer;

/**
 * @description: 观察者模式
 * @author: moshiqing
 * @time: 2020/3/25 10:13
 */
public class MySubject extends AbstractSubject {

    @Override
    public void operation() {
        System.out.println("update self!");
        notifyObservers();
    }

    public static void main(String[] args) {
        Subject subject = new MySubject();
        subject.add(new Observer1());
        subject.add(new Observer2());
        subject.operation();
        subject.del(new Observer1());
        subject.operation();
    }
}
