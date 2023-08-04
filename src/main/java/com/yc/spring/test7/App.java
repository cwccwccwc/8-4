package com.yc.spring.test7;

import com.yc.spring.test7.user.BankAccount;
import com.yc.spring.test7.user.BankAccountDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        ApplicationContext ac =new AnnotationConfigApplicationContext(Config.class);

        BankAccountDao bad=(BankAccountDao)ac.getBean("bankAccountDao");
        List<BankAccount> all = bad.findAll();
        for (BankAccount ba:all){
            System.out.println(ba);
        }
    }
}
