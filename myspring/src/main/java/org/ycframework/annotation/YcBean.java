package org.ycframework.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Autowired
public @interface YcBean {
    String[] value() default {};//修改在spring容器中的id名
}
