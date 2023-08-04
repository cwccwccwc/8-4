package com.yc.configAndDemo;

import com.yc.service.GoodsBiz;
import com.yc.service.GoodsBizImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(TestMain.class);
        ApplicationContext ac=new AnnotationConfigApplicationContext(Config.class);
        GoodsBiz gb = (GoodsBiz) ac.getBean("gb");
//        gb.add("原神！");
        gb.returning();
    }
}
