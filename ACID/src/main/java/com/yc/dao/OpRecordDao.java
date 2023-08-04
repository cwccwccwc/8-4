package com.yc.dao;

import com.yc.bean.OpRecord;

import java.util.List;

public interface OpRecordDao {
    //设计日志的添加接口方法
    public void insertOpRecord(OpRecord opRecord);

    //查询一个用户的所有日志
    public List<OpRecord> findOpRecord(int accountid);

    //查询accountid账户 optype类型 的操作 根据时间排序
    public List<OpRecord> findOpRecord(int accountid,String opType);

    //待开发

}
