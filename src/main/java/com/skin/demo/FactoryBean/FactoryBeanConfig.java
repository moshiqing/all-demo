package com.skin.demo.FactoryBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryBeanConfig {

    @Bean
    public DbFactoryBean dbFactoryBean(){
        return new DbFactoryBean();
    }

    @Bean
    public FactoryBeanUser factoryBeanUser(){
        return new FactoryBeanUser();
    }
}
