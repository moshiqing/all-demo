package com.skin.demo.AbstractAndInteface;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.BeanNameAware;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

@Getter
@Setter
public class dog extends animal {

    private int i = 0;

    private void justDog() {
        System.out.println("狗儿的专属方法");
    }

    @Override
    void run() {
        System.out.println("狗儿必须跑");
    }


    public static void main(String[] args) throws Exception {

       /* //子类转为父类
        animal a = new dog();
        a.run();
        int i = ((dog) a).getI();
        int c = a.getC();
        System.out.println(i);
        System.out.println(c);

        dog dog1=(dog)a;
        dog1.run();
        int i1 = dog1.getI();
        System.out.println(i1);*/

        dog d = new dog();
        animal a = new animal();
        System.out.println(d);
        System.out.println(a);
        ;


    }

}
