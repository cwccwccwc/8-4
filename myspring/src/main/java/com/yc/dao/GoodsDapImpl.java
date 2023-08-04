package com.yc.dao;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class GoodsDapImpl implements GoodsDao{
    @Override
    public void add(String gname) {
        System.out.println("GoodsDapImpl添加了"+gname);
    }
}
