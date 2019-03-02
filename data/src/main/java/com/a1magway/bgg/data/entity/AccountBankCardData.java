package com.a1magway.bgg.data.entity;

import java.io.Serializable;

/**
 * Created by enid on 2018/6/6.
 */

public class AccountBankCardData implements Serializable{
    private int id;// – 信息id
    private String realname;// – 真实姓名
    private String idNumber;//  – 身份证号码
    private String accountNumber = "";//  - 银行卡号
    private String accountType = "";//  – 卡类型
    private String accountOwner;//  – 银行卡归属
    private String phoneNumber;//  – 电话号码
    private String abbreviation;// – 银行英文缩写

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
