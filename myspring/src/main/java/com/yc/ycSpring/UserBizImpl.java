package com.yc.ycSpring;

import org.ycframework.annotation.YcReSource;
import org.ycframework.annotation.YcService;

@YcService
public class UserBizImpl implements UserBiz {
    @YcReSource(name = "userDaoImpl")
    private UserDaoImpl userDao;

    @Override
    public void add(String uname) {
        userDao.add(uname);
        System.out.println("业务层调用add");
    }
}
