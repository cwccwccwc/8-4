package com.yc.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*@Component
@Aspect
@Order(100)*/
public class Aspect2 {
    //切入表达式
    @Pointcut("execution(* com.yc.service..GoodsBizImpl.add*(..))")
    public void abc() {}

    @Before("abc()")
    public void showInfo() {
        System.out.println("Aspect2!切进来了");
    }
}
