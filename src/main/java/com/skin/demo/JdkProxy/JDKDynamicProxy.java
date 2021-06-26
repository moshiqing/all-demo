package com.skin.demo.JdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/3/25 17:39
 */
public class JDKDynamicProxy implements InvocationHandler {

    private Object object;

    public JDKDynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Do something before");
        Object result = method.invoke(object, args);
        System.out.println("Do something after");
        return result;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) {
        LastSubject subject = new LastSubject();
        subject.dosomthing();
        System.out.println("=============原始的情况=============");
     /*   ClassLoader classLoader = invocationHandler.getClass().getClassLoader();
        System.out.println(classLoader.toString());
        Class<?>[] interfaces = subject.getClass().getInterfaces();
        System.out.println(interfaces.toString());
        Subject s =(Subject) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        System.out.println(s.toString());*/
        JDKDynamicProxy invocationHandler = new JDKDynamicProxy(subject);
        Subject subject1 = (Subject) invocationHandler.getProxy();
        subject1.dosomthing();

    }
}
