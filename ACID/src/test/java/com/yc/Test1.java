package com.yc;

import com.yc.biz.AccountitBiz;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Test1 extends TestCase {
    //DI
    @Autowired
    private AccountitBiz accounitBiz;

    //单元测试用例
    @Test
    public void testAdd(){
        int x=3,y=4;
        Assert.assertEquals(7,x+y);
    }}
