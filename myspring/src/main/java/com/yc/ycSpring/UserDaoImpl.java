package com.yc.ycSpring;

import org.ycframework.annotation.YcLazy;
import org.ycframework.annotation.YcRepository;

@YcRepository
@YcLazy
public class UserDaoImpl implements UserDao {
    @Override
    public void add(String uname) {
        System.out.println("dao添加了"+uname);
    }
}
