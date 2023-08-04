package com.yc.spring.test3.system;
/*
    容器过滤接口
* */
public interface ContainerFilter {
    /*
    * 判断此对象是否为有效对象
    *  obj
    * */
    public boolean doFilter(Object obj);
}
