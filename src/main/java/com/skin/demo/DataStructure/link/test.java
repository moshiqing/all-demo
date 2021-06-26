package com.skin.demo.DataStructure.link;

import java.util.concurrent.atomic.AtomicInteger;

public class test {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList<Integer>();
        for (int i = 1; i < 10; i++) {
            linkedList.addFirst(i);
        }
        System.out.println(linkedList.toString());

    }
}
