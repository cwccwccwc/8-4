package com.yc.staticproxy;

import com.yc.biz.OrderBiz;

public class StaticProxy implements OrderBiz {
    private OrderBiz orderBiz;

    public StaticProxy(OrderBiz orderBiz) {
        this.orderBiz = orderBiz;
    }


    @Override
    public void addOrder(int pid, int num) {
        //前置增强
        sayHello();
        orderBiz.addOrder(pid,num);
        //后置增强
        sayBey();
    }

    @Override
    public void findOrder() {

    }

    private void sayHello() {
        System.out.println("扣你鸡哇");
    }

    private void sayBey() {
        System.out.println("撒有啦啦");
    }
}
