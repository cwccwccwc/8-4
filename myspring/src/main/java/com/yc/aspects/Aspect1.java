package com.yc.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*@Component
@Aspect
@Order(1)*/
public class Aspect1 {
    //切入表达式
    @Pointcut("execution(* com.yc.service..GoodsBizImpl.add*(..))")
    public void abc() {}

    @Before("abc()")
    public void showInfo(JoinPoint jp) {
        System.out.println("连接点的信息如下:");
        System.out.println("目标类:"+jp.getTarget()+",方法的签名:"+jp.getSignature());
        System.out.println("加强增强的方法中的参数值");

        Object[] args = jp.getArgs();
        if (args!=null&&args.length>0){
            for (Object arg:args){
                System.out.println(arg);
            }
        }
    }
}
