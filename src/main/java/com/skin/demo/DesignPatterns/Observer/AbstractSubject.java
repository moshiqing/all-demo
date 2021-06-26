package com.skin.demo.DesignPatterns.Observer;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/3/25 10:08
 */
public class AbstractSubject implements Subject {

    private Vector<Observer> vector = new Vector<Observer>();

    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Enumeration<Observer> enumo = vector.elements();
        while (enumo.hasMoreElements()) {
            enumo.nextElement().update();
        }
    }

    @Override
    public void operation() {

    }
}
