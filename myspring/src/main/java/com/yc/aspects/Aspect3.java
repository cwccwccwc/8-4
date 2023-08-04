package com.yc.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aspect3 {
    //切入表达式
    @Pointcut("execution(* com.yc.service..GoodsBizImpl.returning(..))")
    public void abc() {}

    @AfterReturning(pointcut = "abc()",returning = "retVal")
    public void show(JoinPoint jp,Object retVal){
        System.out.println(jp.getSignature());
        System.out.println("返回的结果为:"+retVal);
    }

   /* @AfterThrowing(pointcut = "abc()",throwing = "ex")
    public void show2(RuntimeException ex){
        System.out.println("AfterThrowing,被增强的方法的有异常报出"+ex);
    }*/

    /*@After("abc()")
    public void show3(){
        System.out.println("不管是否正常返回，都将执行此代码");
    }*/

//    @Around("abc()")
    public Object show4(ProceedingJoinPoint pjp) throws Throwable {   //ProceedingJoinPoint代表连接点
        System.out.println("RateAspect进来了");
        long startTime = System.currentTimeMillis();

        Object obj=pjp.proceed();

        long endTime = System.currentTimeMillis();
        System.out.println("该方法运行了："+(endTime-startTime));

        return obj;
    }

    @Around( "abc()")
    public Object show5(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("方法执行前...");
        Object obj=pjp.proceed();

        System.out.println("方法执行后...");
        return obj;
    }
}
