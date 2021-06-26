package com.skin.demo.Factory;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Factory {

    private Object tager;

    Factory(Object tager) {
        this.tager = tager;
    }

    public Object getFactory() {
        Object o = Proxy.newProxyInstance(tager.getClass().getClassLoader(), tager.getClass().getInterfaces(), new FactoryProxy());
        return o;

    }

    class FactoryProxy implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("代理前");
            Object obj = method.invoke(tager, args);
            System.out.println("代理后");
            return obj;
        }
    }

    public static void main(String[] args) {
        Subject subject1 = (Subject) new Factory(new SubjectImpl()).getFactory();
        System.out.println("2-------" + subject1.getClass());
        subject1.hello();

    }
}
