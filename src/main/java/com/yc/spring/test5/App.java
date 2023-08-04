package com.yc.spring.test5;

import com.yc.spring.test5.IMP.q;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ac=new AnnotationConfigApplicationContext(Config.class);
        q q = (q)ac.getBean("q");
        q.add("牛逼！");
    }


}
