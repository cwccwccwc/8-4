package com.yc.jdkproxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * 生成 代理类 的工具类
 * */
public class JdkProxyTool implements InvocationHandler {
    //目标类
    private Object target;

    public JdkProxyTool(Object target) {
        this.target = target;
    }

    //生成代理对象的方法
    public Object createProxy() {
        Object proxy = Proxy.newProxyInstance(JdkProxyTool.class.getClassLoader(),
                target.getClass().getInterfaces(),
                this);
        return proxy;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("a")) {
            sayHello();
        }
        Object invoke = method.invoke(target, args);

        return invoke;

    }

    private void sayHello() {
        System.out.println("萨瓦迪卡");
    }
}
