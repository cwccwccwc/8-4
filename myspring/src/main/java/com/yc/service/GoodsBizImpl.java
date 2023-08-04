package com.yc.service;

import com.yc.dao.GoodsDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service(value = "gb")
public class GoodsBizImpl implements GoodsBiz{
    @Resource(name = "goodsDapImpl")
    private GoodsDao goodsDao;
    @Override
    public void add(String gname) {
        goodsDao.add(gname);
        System.out.println("GoodsBizImpl也添加了"+gname);
    }

    @Override
    public int returning() {
        Random r = new Random();
        int i = r.nextInt(2);
        if (i==0){
//            throw new RuntimeException("非正常返回");
        }
        System.out.println("正常返回...");
        return 9999;
    }
}
