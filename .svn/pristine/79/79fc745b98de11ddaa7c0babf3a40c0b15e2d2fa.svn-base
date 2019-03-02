package com.a1magway.bgg.data.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单详情
 * Created by jph on 2017/8/19.
 */
public class OrderDetails implements Serializable {


    private static final long serialVersionUID = 1421642303247801817L;
    /**
     * address : {"orderuserName":"哈哈","orderuserPhone":"18328711561","orderuserDetailAddress":"四川开发计划hfk ","postCode":"610000"}
     * skuList : [{"productName":"LA MER（海蓝之谜）提升紧致精华面膜 50ML","brand":"LA MER（海蓝之谜）","listPrice":"2200.00","sellPrice":"1540.00","specs":"50ml","count":2,"skuCover":["fdsfdsfkpok.jpg","fhghyuuyuyuyyu.jpg"],"productCode":"null待输入"}]
     * orderNum : 2017051100012
     * orderCreatTime : 2017-05-11 13:57:34
     * payType : 0
     * expressName : null
     * expressNum : null
     * sellPrice : 3820.00
     * tax : 222.00
     * freight : 0.00
     * remark : 要各种样式的
     * payTime : 15
     * totalPrice : 4444.00
     * orderStatus : 3
     */

    private OrderAddress address;
    private String orderNum;
    private Date orderCreatTime;
    @PayType
    private int payType;
    private Object expressName;
    private Object expressNum;
    private String sellPrice;
    private String tax;
    private String freight;
    private String remark;
    private int payTime;
    private String totalPrice;
    private String totalListPrice;
    private String totalDiscount;
    private int orderStatus;
    private int packageMail;//是否包邮（0 否 1是）
    private List<OrderDetailsCommodity> skuList;
    private String activityType;//2为拼团商品

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public OrderAddress getAddress() {
        return address;
    }

    public void setAddress(OrderAddress address) {
        this.address = address;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderCreatTime() {
        return orderCreatTime;
    }

    public void setOrderCreatTime(Date orderCreatTime) {
        this.orderCreatTime = orderCreatTime;
    }

    @PayType
    public int getPayType() {
        return payType;
    }

    public void setPayType(@PayType int payType) {
        this.payType = payType;
    }

    public Object getExpressName() {
        return expressName;
    }

    public void setExpressName(Object expressName) {
        this.expressName = expressName;
    }

    public Object getExpressNum() {
        return expressNum;
    }

    public void setExpressNum(Object expressNum) {
        this.expressNum = expressNum;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getPayTime() {
        return payTime;
    }

    public void setPayTime(int payTime) {
        this.payTime = payTime;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderDetailsCommodity> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<OrderDetailsCommodity> skuList) {
        this.skuList = skuList;
    }

    public String getTotalListPrice() {
        return totalListPrice;
    }

    public void setTotalListPrice(String totalListPrice) {
        this.totalListPrice = totalListPrice;
    }


    public int getPackageMail() {
        return packageMail;
    }


    public void setPackageMail(int packageMail) {
        this.packageMail = packageMail;
    }

    public String getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(String totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
}
