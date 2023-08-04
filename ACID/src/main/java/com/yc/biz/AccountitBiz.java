package com.yc.biz;

import com.yc.bean.Account;

/**
 * @program: mavenProject
 * @description:
 * @author: Joker
 * @create: 2023-08-02 14:15
 */
public interface AccountitBiz {
    public void addAccount(int accountid, double money);

    public Account openAccount(double money);

    public Account deposite(int accountid , double money);

    public Account deposite(int accountid , double money,Integer transferId);

    public Account withdraw(int accountid , double money);

    public Account withdraw(int accountid, double money,Integer transferId);

    public Account transfer(int accounId,double money,int toAccountId);

    public  Account findAccount(int accountid);
}
