package com.skin.demo.Wildcards;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/3/25 16:42
 */
public class Plate<T> {

    private T item;

    Plate(T t) {
        this.item = t;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public static void main(String[] args) {
        Plate<? extends Fruit> p = new Plate<Apple>(new Apple());
    }
}
