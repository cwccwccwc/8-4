package com;

import com.yc.Test1;
import com.yc.Test2;
import com.yc.impl.AccountDaoJdbcTemplateImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// 测试套件
@RunWith(Suite.class)
@Suite.SuiteClasses({Test1.class, Test2.class, AccountDaoJdbcTemplateImplTest.class})
public class TestSuit {
}
