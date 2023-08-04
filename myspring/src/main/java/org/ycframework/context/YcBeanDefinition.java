package org.ycframework.context;

import lombok.Data;

/*
* 对一个Bean的特征的包装的类
* 特征：scope(singleton/prototype/...)
*       lazy(true/false) 懒加载
*       primary:主实例|优先实例    UserDao接口 @Primary UserDaoMybatisImpl
*                               业务层@Autowired+@Qualifier
*                                   @Autowired+@Primary
* */
@Data
public class YcBeanDefinition {
    private boolean isLazy;
    private String scope="singleton";
    private boolean isPrimary;
    //...

    private String classInfo;
}
