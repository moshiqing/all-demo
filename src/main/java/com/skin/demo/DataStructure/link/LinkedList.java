package com.skin.demo.DataStructure.link;

public class LinkedList<E> {

    /**
     * 基础元素
     */
    private class Node {

        /**
         * 当前的元素
         */
        public E e;

        /**
         * 下一个元素的指针
         */
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return super.toString();
        }

    }

    /**
     * 头结点的元素
     */
    private Node head;
    /**
     * 链表的容量
     */
    private int size;

    public LinkedList(Node head, int size) {
        this.head = head;
        this.size = size;
    }

    public LinkedList() {
        head = new Node(null, null);
        size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) {
//        BST node=new BST(e);
//        node.next=head;
//        head=node;
        add(0, e);

        size++;
    }

    public void add(int index, E e) {
//        if(index==0){
//            addFirst(e);
//        }else{
        //拥有虚拟头结点 不作为实际作用set
        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);
        //两个的实现是一样的
//                Node node=new Node(e);
//                node.next=prev.next;
//                prev.next=node;

        size++;

    }
//    }

    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        Node cur = head.next;
        for (int i = 0; i < index; i++) {
            //进行遍历
            cur = cur.next;
        }
        return cur.e;
    }

    public void set(int index, E e) {
        Node cur = head.next;
        for (int i = 0; i < index; i++) {
            //进行遍历
            cur.e = e;
        }
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public boolean contains(E e) {
        Node cur = head.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index) {
        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        return delNode.e;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node cur = head.next;

        while (cur != null) {
            stringBuilder.append(cur.e + "->");
            cur = cur.next;
        }
        stringBuilder.append("NULL");
        return stringBuilder.toString();
    }
}
