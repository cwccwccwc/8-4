package com.yc.ycSpring;

import org.ycframework.context.YcAnnotationConfigApplicationContext;
import org.ycframework.context.YcApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        /*Logger logger = LoggerFactory.getLogger(TestMain.class);
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("trace");*/
        YcApplicationContext ac=new YcAnnotationConfigApplicationContext(MyConfig.class);
        UserBizImpl userBizImpl = (UserBizImpl) ac.getBean("userBizImpl");
        userBizImpl.add("张三");
    }
}
