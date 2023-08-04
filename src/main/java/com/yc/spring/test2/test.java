package com.yc.spring.test2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test {
    public static void main(String[] args) {
        ApplicationContext anContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Apple a = (Apple) anContext.getBean("a");
        System.out.println(a);
    }
}
