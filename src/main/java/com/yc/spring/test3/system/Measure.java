package com.yc.spring.test3.system;

/*
*  测量 接口
* */
public interface Measure {
    /* obj 待测量的对象
        return 测量的值
    * */
    public double doMeasure(Object obj);
}
