package com.yc.spring.test8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})         //Type表示这个注解只能放在 类 接口
@Retention(RetentionPolicy.RUNTIME)     /*
RetentionPolicy.RUNTIME（运行时级别）：
注解保留在编译后的字节码中，并且可以在运行时通过反射来获取和处理注解信息。
 这使得我们可以在运行时访问并解析注解，在开发框架、AOP、单元测试等场景中非常有用。
*/
public @interface CwcBean {
}
