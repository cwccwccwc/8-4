package com.yc.spring.test6;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;
import java.util.concurrent.ExecutorService;

public class App {
    public static void main(String[] args) {
        ApplicationContext ac=new AnnotationConfigApplicationContext(Config.class);
        ExecutorService es=(ExecutorService)ac.getBean("threadPoolExecutor");
        for (int i = 0; i < 5; i++) {
            es.submit(()->{
               while (true){
                   System.out.println(Thread.currentThread().getName()+"提醒您当前时间为"+new Date());
                   Thread.sleep(1000);
               }
            });
        }
    }
}
