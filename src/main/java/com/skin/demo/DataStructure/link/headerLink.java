package com.skin.demo.DataStructure.link;

import java.util.Random;
import java.util.Scanner;

public class headerLink {

    //创建一个存储数据的属性
    int data;
    //创建存储下一个节点地址的属性
    headerLink next;

    static int length = 0;

    /**
     * 创建一个链表
     *
     * @param len 产生数据的长度
     * @return 链表第一个节点地址
     */
    public static headerLink creat(int len) {
        //定义随机对象
        Random r = new Random();
        //定义链表的节点
        headerLink newnode, header;
        //header永远存储第一个节点的地址，tailer永远存储最后一个节点的地址
        header = null;
        for (int i = 0; i < len; i++) {
            //生成一个随机数字
            int temp = r.nextInt(100);
            System.out.println("当前节点" + temp);
            //创建一个临时节点
            newnode = new headerLink();
            //长度
            length++;
            //为属性赋值
            newnode.data = temp;
            //判断当前链表是否第一次赋值
            if (header == null) {
                header = newnode;
            } else {
                //将新节点连接到链表的头部
                newnode.next = header;
                //header永远存储第一个节点的地址
                header = newnode;
            }
        }
        return header;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入长度：");
        int l = sc.nextInt();
        System.out.println("打印数组：");
        //调用数组生成方法 传入键盘值l
        headerLink head = headerLink.creat(l);
        for (int i = 0; i < headerLink.length; i++) {
            System.out.print(head.data + " ");
            //把当前对象的下一个对象地址传给当前对象
            head = head.next;
        }
    }

}
