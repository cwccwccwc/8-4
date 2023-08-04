package com.yc.bean;

public enum OpType {

    WITHDRAW("withdraw",1),DEPOSITE("deposite",2),TRANSFER("transfer",3);
    private String key;
    private int valuel;

    OpType(String key, int valuel) {
        this.key = key;
        this.valuel = valuel;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValuel() {
        return valuel;
    }

    public void setValuel(int valuel) {
        this.valuel = valuel;
    }
}
