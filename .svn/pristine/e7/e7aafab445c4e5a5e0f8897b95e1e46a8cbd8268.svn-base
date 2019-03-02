package com.a1magway.bgg.data.entity;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * 地址实例
 * Created by lyx on 2017/7/31.
 */
public class AddressData implements Serializable, Cloneable{

    /**
     * id : 188
     * orderuserName : 类型
     * orderuserPhone : 18328711561
     * orderuserAddress : 成都
     * orderuserDetailAddress : 科西二路
     * areaId : 123
     * postCode : 61000
     * userId : 14
     * createTime : 2017-04-21 14:14:27
     * updateTime : null
     * isDefault : 1
     * isEnable : 0
     */

    private int id;
    private String orderuserName;
    private String orderuserPhone;
    private String orderuserAddress;
    private String orderuserDetailAddress;
    private int areaId;
    private String postCode;
    private int userId;
    private String createTime;
    private Object updateTime;
    private int isDefault;
    private int isEnable;
    private String realName;
    private String idCard;

    public static AddressData objectFromData(String str) {

        return new Gson().fromJson(str, AddressData.class);
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderuserName() {
        return orderuserName;
    }

    public void setOrderuserName(String orderuserName) {
        this.orderuserName = orderuserName;
    }

    public String getOrderuserPhone() {
        return orderuserPhone;
    }

    public void setOrderuserPhone(String orderuserPhone) {
        this.orderuserPhone = orderuserPhone;
    }

    public String getOrderuserAddress() {
        return orderuserAddress;
    }

    public void setOrderuserAddress(String orderuserAddress) {
        this.orderuserAddress = orderuserAddress;
    }

    public String getOrderuserDetailAddress() {
        return orderuserDetailAddress;
    }

    public void setOrderuserDetailAddress(String orderuserDetailAddress) {
        this.orderuserDetailAddress = orderuserDetailAddress;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public boolean getIsDefault() {
        return isDefault == 1 ? true : false;
    }

    public int getIsDefaultInt() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault ? 1 : 0;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    @Override
    public Object clone() {
        AddressData data = null;
        try {
            data = (AddressData) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return data;
    }
}
