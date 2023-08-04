package com.yc.spring.test5.IMP;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class q {
    @Resource(name = "userImp")
    private UserImp userImp;
    public void add(String name){
        userImp.add(name);
    }
}
