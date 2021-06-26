package com.skin.demo.hashmap;

import java.util.HashMap;
import java.util.Map;

public class Node {


    static Node node = new Node();

    int data;
    Node next = null;

    Node() {

    }

    public void touchafa(int n1) {
        Node node1 = new Node();
        node1.data = n1;

        if (node.next == null) {
            node.next = node1;

        } else {
            node1.next = node.next;
            node.next = node1;

        }
        System.out.println("之前:" + node1.data);
    }

    public void display() {
        Node node1 = new Node();
        node1 = node.next;

        while (node1 != null) {
            System.out.println(node1.data);
            node1 = node1.next;

        }
    }


    public static void main(String[] args) {
        Node dlb = new Node();
        dlb.touchafa(1);
        dlb.touchafa(2);
        dlb.touchafa(3);
        dlb.touchafa(4);
        dlb.touchafa(5);
        dlb.touchafa(6);
        dlb.display();
    }
}
