package com.yc.biz;

public class OrderBizImpl implements OrderBiz{
    @Override
    public void addOrder(int pid, int num) {
        System.out.println(pid+"增加了数量"+num);
    }

    @Override
    public void findOrder() {
        System.out.println("查看订单...........");
    }
}
