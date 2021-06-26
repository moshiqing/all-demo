package com.skin.demo.JdkProxy;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/3/25 17:39
 */
public class RealSubject implements Subject {
    @Override
    public void dosomthing() {
        System.out.println("接口类实现了");
    }

    public void myselfMethod(){
        System.out.println("实现接口之后我自己想要做的事情");
    }
}
