package com.yc;

import com.alibaba.druid.pool.DruidDataSource;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class,DataSourceConfig.class})
@Log4j2
public class Test2 extends TestCase {
    @Autowired
    private DataSourceConfig dsc;
    @Autowired
    private Environment env;
    @Qualifier("dataSource")
    @Autowired
    private DataSource ds;

    @Qualifier("dbcpDataSource")
    @Autowired
    private DataSource dpds;

    @Qualifier("druidDataSource")
    @Autowired
    private DruidDataSource druidDataSource;


    @Autowired
    private TransactionManager tx;

    @Test
    public void testTransactionManager(){
        log.info(tx);
    }

    @Test
    public void testPropertySource(){
        Assert.assertEquals("root",dsc.getUser());
        log.info(dsc.getUser());
    }

    @Test
    public void testEnv(){
        log.info(env.getProperty("password")+"\t"+env.getProperty("user"));
    }

    @Test
    public void testDsc() throws SQLException {
        Assert.assertNotNull(ds.getConnection());
    }

    @Test
    public void testDpds() throws SQLException {
        Assert.assertNotNull(dpds.getConnection());
    }

    @Test
    public void testDruid() throws SQLException {
        Assert.assertNotNull(druidDataSource.getConnection());
    }
}
