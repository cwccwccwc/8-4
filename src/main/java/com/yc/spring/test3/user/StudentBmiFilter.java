package com.yc.spring.test3.user;

import com.yc.spring.test3.system.ContainerFilter;
import org.springframework.stereotype.Component;

@Component("bmiFilter")
public class StudentBmiFilter implements ContainerFilter {
    @Override
    public boolean doFilter(Object obj) {
        if (obj==null||!(obj instanceof Student)){
            return false;

        }
        Student s=(Student) obj;
        if (s.getName()==null||"".equalsIgnoreCase(s.getName())){
            return false;
        }
        if (s.getHeight()<1||s.getHeight()>2.4){
            System.out.println("身高数据异常"+obj.toString());
            return false;
        }
        if (s.getWeight()<30||s.getWeight()>500){
            System.out.println("体重数据异常"+obj.toString());
            return false;
        }
        return true;
    }
}
