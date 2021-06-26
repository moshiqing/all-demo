package com.skin.demo.FactoryBean;

import org.springframework.beans.factory.FactoryBean;

public class DbFactoryBean implements FactoryBean<FactoryBeanUser> {

    @Override
    public FactoryBeanUser getObject() throws Exception {
        System.out.println("DbFactoryBean执行...");
        FactoryBeanUser fb =new FactoryBeanUser();
        fb.setUserName("测试类");
        return fb;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    public void dbAlone(){
        System.out.println("db独有方法");
    }
}
