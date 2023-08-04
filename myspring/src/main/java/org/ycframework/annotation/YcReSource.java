package org.ycframework.annotation;


import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
public @interface YcReSource {
    String name() default "";  //要装配的对象的id名
}
