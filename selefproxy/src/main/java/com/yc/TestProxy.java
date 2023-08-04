package com.yc;

import com.yc.biz.OrderBiz;
import com.yc.biz.OrderBizImpl;
import com.yc.jdkproxy.JdkProxyTool;

public class TestProxy {
    public static void main(String[] args) {
        //设置代理生成的字节码，保存到当前目录
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
//        System.out.println(System.getProperty("java.io.tmpdir"));
        OrderBiz biz = new OrderBizImpl();
        /*StaticProxy staticProxy = new StaticProxy(biz);
        staticProxy.addOrder(1,99);*/
        JdkProxyTool jdkProxyTool = new JdkProxyTool(biz);
        OrderBiz proxy = (OrderBiz)jdkProxyTool.createProxy();
        proxy.addOrder(1,99);
        proxy.findOrder();
    }
}
