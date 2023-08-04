package com.yc.spring.test3.user;

import com.yc.spring.test3.system.Measure;
import org.springframework.stereotype.Component;

@Component("bmiMeasure")
public class StudentBmiMeasure implements Measure {
    @Override
    public double doMeasure(Object obj) {
        if (obj==null||!(obj instanceof  Student)){
            throw new RuntimeException("待数据异常");
        }
        Student s=(Student) obj;
        return s.getWeight()/(s.getHeight()*s.getHeight());
    }
}
