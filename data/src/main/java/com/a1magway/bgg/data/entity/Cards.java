package com.a1magway.bgg.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * author: Beaven
 * date: 2017/10/13 11:44
 */

public class Cards implements Serializable {

    private static final long serialVersionUID = 5710173762090559040L;

    private String startTime;
    private List<Permission> permissionList;
    private String endTime;
    private int cardType;
    private String cardNumber;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public List<Permission> getPermission() {
        return permissionList;
    }

    public void setPermission(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
