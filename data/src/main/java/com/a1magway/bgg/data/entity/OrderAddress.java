package com.a1magway.bgg.data.entity;

import java.io.Serializable;

/**
 * 订单详情的收获地址
 * Created by jph on 2017/8/19.
 */
public class OrderAddress implements Serializable {

    private static final long serialVersionUID = 3907397556709680191L;
    /**
     * orderuserName : 哈哈
     * orderuserPhone : 18328711561
     * orderuserDetailAddress : 四川开发计划hfk
     * postCode : 610000
     */

    private String orderuserName;
    private String orderuserPhone;
    private String orderuserDetailAddress;
    private String postCode;

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

    public String getOrderuserDetailAddress() {
        return orderuserDetailAddress;
    }

    public void setOrderuserDetailAddress(String orderuserDetailAddress) {
        this.orderuserDetailAddress = orderuserDetailAddress;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
