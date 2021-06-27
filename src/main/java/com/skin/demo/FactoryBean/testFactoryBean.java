package com.skin.demo.FactoryBean;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class testFactoryBean {

    @Test
    public void testFactoryBean(){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
        //通过获取FactoryBean对象我们获取到的是FactoryBeanUser实例
        FactoryBeanUser factoryBeanUser = (FactoryBeanUser) context.getBean("dbFactoryBean");
        System.out.println(factoryBeanUser.getUserName());
        DbFactoryBean dbFactoryBean = (DbFactoryBean) context.getBean("&dbFactoryBean");
        dbFactoryBean.dbAlone();
        FactoryBeanUser factoryBeanUser1 = (FactoryBeanUser)context.getBean("factoryBeanUser");
        String userName = factoryBeanUser1.getUserName();
        System.out.println(userName);
    }
}
