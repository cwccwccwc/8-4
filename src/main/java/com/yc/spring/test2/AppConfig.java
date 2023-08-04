package com.yc.spring.test2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration   //此注解表明这是一个配置类  相当于 beans.xml
public class AppConfig {
    @Bean
    public Apple a(){
        Apple apple = new Apple();
        apple.setId(99);
        return apple;
    }
}
