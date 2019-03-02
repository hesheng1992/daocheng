package com.a1magway.bgg.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 校验下单商品返回的数据
 * Created by jph on 2017/8/23.
 */
public class CheckOrderProResult implements Serializable {


    private static final long serialVersionUID = -4133803296653195644L;

    public static final int PACKAGE_MAIL_OFF = 0;
    public static final int PACKAGE_MAIL_ON = 1;
    /**
     * packageMail : true
     * totalCost : 6207.00
     * count : 6
     * tax : 2498.00
     * freight : 360.00
     * totalPrice : 3349.00
     */

    private int packageMail;//是否包邮（0 否 1是）
    private String totalCost;//支付价格
    private int count;
    private String tax;
    private String freight;
    private String totalPrice;//商品总价
    private String totalListPrice;//原总价
    private String sellPrice;
    private int isOverSea;//1是海外仓;0不是海外仓
    private List<OrderDetailsCommodity> shopcartList;
    private int collageOrderId;//拼团订单ID
    private int buyStatus;//0普通购买，1拼团购买，2拼团单独购买

    public int getBuyStatus() {
        return buyStatus;
    }

    public void setBuyStatus(int buyStatus) {
        this.buyStatus = buyStatus;
    }

    public int getCollageOrderId() {
        return collageOrderId;
    }

    public void setCollageOrderId(int collageOrderId) {
        this.collageOrderId = collageOrderId;
    }

    public List<OrderDetailsCommodity> getShopcartList() {
        return shopcartList;
    }

    public void setShopcartList(List<OrderDetailsCommodity> shopcartList) {
        this.shopcartList = shopcartList;
    }

    public int getIsOverSea() {
        return isOverSea;
    }

    public void setIsOverSea(int isOverSea) {
        this.isOverSea = isOverSea;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getPackageMail() {
        return packageMail;
    }

    public void setPackageMail(int packageMail) {
        this.packageMail = packageMail;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalListPrice() {
        return totalListPrice;
    }

    public void setTotalListPrice(String totalListPrice) {
        this.totalListPrice = totalListPrice;
    }
}
