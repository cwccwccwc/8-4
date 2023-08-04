package com.yc.spring.test5.IMP;

import com.yc.spring.test5.DAO.UserDao;
import org.springframework.stereotype.Component;

@Component
public class UserImp  implements UserDao{


    @Override
    public void add(String uname) {
        System.out.println("add "+uname);
    }
}
