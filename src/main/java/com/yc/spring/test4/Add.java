package com.yc.spring.test4;

import org.springframework.stereotype.Component;

@Component
public class Add implements JiShuan{
    @Override
    public int add(Num num) {
        return num.getX()+num.getY();
    }

    @Override
    public int mul(Num num) {
        return num.getX()*num.getY();
    }
}
