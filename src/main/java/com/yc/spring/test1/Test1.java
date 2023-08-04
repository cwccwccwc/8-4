package com.yc.spring.test1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {
    public static void main(String[] args) {
        /*
        * 引入spring的IOC机制
        *   IOC：控制反转 由spring来创建 对象Student
        *           ClassPathXmlApplicationContext：类路径下的 xml文件
        * */
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student s =(Student) context.getBean("s");
        System.out.println(s);
    }
}
