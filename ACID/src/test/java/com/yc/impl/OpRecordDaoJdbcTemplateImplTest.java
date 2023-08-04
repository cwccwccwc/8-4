package com.yc.impl;

import com.yc.Config;
import com.yc.DataSourceConfig;
import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import com.yc.dao.OpRecordDao;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class, DataSourceConfig.class})
@Log4j2
public class OpRecordDaoJdbcTemplateImplTest extends TestCase {
    @Autowired
    private OpRecordDao opRecordDao;

    @Test
    public void insertOpRecord() {
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(6);
        opRecord.setOpmoney(5);
        opRecord.setOptype(OpType.DEPOSITE);
        this.opRecordDao.insertOpRecord(opRecord);
    }

    @Test
    public void findOpRecord() {
        List<OpRecord> opRecord = this.opRecordDao.findOpRecord(2);
        log.info(opRecord);
    }

    @Test
    public void testFindOpRecord() {
        List<OpRecord> opRecord = this.opRecordDao.findOpRecord(2,"DEPOSITE");
        log.info(opRecord);
    }
}