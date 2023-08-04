package com.yc.spring.test3.system;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/*
* 1.能存大量对象（大学生 bmi | 。。。） =》集合
* 2.测量的接口 =》具体怎么测，由用户决定
* 3.筛选的接口 =》具体怎么筛选，由用户决定
* 4.返回最大值对象 最小值对象
*
* */
//TODO：要用spring完成托管
@Component
public class Container <T>{
    private List<T> objs=new ArrayList<T>();

    //TODO:要使用spring完成DI

    @Resource(name = "bmiMeasure")
    private Measure measure;
    @Resource(name = "bmiFilter")
    private ContainerFilter filter;

    private T max;
    private T min;
    private double avg;
    private double sum;

    public Container() {
        System.out.println("容器构造......");
    }

    /*
    * 添加对象的方法
    * */
    public void add(T t){
        //判断t是否合格 调用筛选的实现
        if (filter!=null){
            if (filter.doFilter(t)==false){
                return;
            }
        }
        //添加到 objs
        objs.add(t);
        //判断大小 记录max min 计算avg  =》调用measure的实现
        if (objs.size()==1){
            max=t;
            min=t;
        }else {
            //调出值，判断大小
            double val = this.measure.doMeasure(t);
            double maxVal = this.measure.doMeasure(this.max);
            double minVal = this.measure.doMeasure(this.min);
            if (val>maxVal){
                max=t;
            }
            if (val<minVal){
                min=t;
            }
        }
        sum+=measure.doMeasure(t);
        avg=sum/objs.size();
    }

    public T getMax() {
        return max;
    }

    public T getMin() {
        return min;
    }

    public double getAvg() {
        return avg;
    }

    /*
    * 有效的测量对象
    * */
    public int size(){
        return objs.size();
    }

    /*
    * 系统复位
    * */
    public void clearAll(){
        objs=new ArrayList<T>();
        measure=null;
        filter=null;
        max=null;
        min=null;
        avg=0;
    }
}
