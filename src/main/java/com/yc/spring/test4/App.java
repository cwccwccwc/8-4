package com.yc.spring.test4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ac=new AnnotationConfigApplicationContext(Config.class);
        JiShuanBiz jiShuanBiz = (JiShuanBiz)ac.getBean("jiShuanBiz");
        jiShuanBiz.add(1,2);
    }
}
