package com.yc.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

public class CustonInvocationHandler implements InvocationHandler {

    private Object target;//目标类的引用

    public CustonInvocationHandler(Object target) {
        this.target = target;
    }

    //生成代理对象的方法
    public Object createProxy(){
        //jdk中提供了 Proxy类 根据接口生成代理对象的方法
        Object proxy = Proxy.newProxyInstance(CustonInvocationHandler.class.getClassLoader(), target.getClass().getInterfaces(), this);
        return proxy;
    }
/*
*       proxy  代理对象  $Proxy01
*       method 调用的方法 sayHello()
*       args   方法的参数值
* */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        showTime();
        //反射机制调用目标类的目标方法
        Object returValue = method.invoke(target, args);
        return returValue;
    }

    //增强方法
    public void showTime(){
        System.out.println("时间为："+new Date());
    }
}
