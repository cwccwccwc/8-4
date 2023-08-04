package com.yc.spring.test4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class JiShuanBiz {
    @Autowired
    public JiShuan jiShuan;

    public void add(int x,int y){
        System.out.println(jiShuan.add(new Num(x,y)));
    }
}
