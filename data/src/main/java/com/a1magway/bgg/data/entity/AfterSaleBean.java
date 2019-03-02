package com.a1magway.bgg.data.entity;

import java.util.List;

/**
 * Created by enid on 2018/9/8.
 */

public class AfterSaleBean {

    /**
     * id : 91
     * orderNum : null
     * type : 1
     * status : 1
     * phone : null
     * consignee : null
     * address : null
     * consigneePhone : null
     * logisticsNum : null
     * reasonId : null
     * reason : null
     * explain : null
     * totalPrice : 1540.0
     * refundNumber : null
     * pictures : null
     * totalCount : null
     * result : null
     * skuList : [{"id":164,"orderId":24102,"commodityId":5195,"commodityMetaValue":"sku","commodityName":"GOLDEN GOOSEXX女休闲鞋","commoditySellPrice":1540,"commodityListPrice":2890,"commodityFoldPrice":null,"commodityNumbers":1,"commoditySpec":"无色 36","commodityCode":"GC0WS590 E37","commodityBrand":"GOLDEN GOOSE","commodityPicturePath":"https://smjdev.1magway.com/pictures/platform/commodity/picture/190010902100225504/1.png","productId":2817,"productType":"0","couponsId":0,"refundStatus":null,"refundId":null}]
     * logs : null
     */

    private int id;
    private Object orderNum;
    private int type;
    private int status;
    private Object phone;
    private Object consignee;
    private Object address;
    private Object consigneePhone;
    private Object logisticsNum;
    private Object reasonId;
    private Object reason;
    private Object explain;
    private double totalPrice;
    private Object refundNumber;
    private Object pictures;
    private Object totalCount;
    private Object result;
    private Object logs;
    private List<OrderCommodity> skuList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Object orderNum) {
        this.orderNum = orderNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getConsignee() {
        return consignee;
    }

    public void setConsignee(Object consignee) {
        this.consignee = consignee;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(Object consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public Object getLogisticsNum() {
        return logisticsNum;
    }

    public void setLogisticsNum(Object logisticsNum) {
        this.logisticsNum = logisticsNum;
    }

    public Object getReasonId() {
        return reasonId;
    }

    public void setReasonId(Object reasonId) {
        this.reasonId = reasonId;
    }

    public Object getReason() {
        return reason;
    }

    public void setReason(Object reason) {
        this.reason = reason;
    }

    public Object getExplain() {
        return explain;
    }

    public void setExplain(Object explain) {
        this.explain = explain;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Object getRefundNumber() {
        return refundNumber;
    }

    public void setRefundNumber(Object refundNumber) {
        this.refundNumber = refundNumber;
    }

    public Object getPictures() {
        return pictures;
    }

    public void setPictures(Object pictures) {
        this.pictures = pictures;
    }

    public Object getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Object totalCount) {
        this.totalCount = totalCount;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getLogs() {
        return logs;
    }

    public void setLogs(Object logs) {
        this.logs = logs;
    }

    public List<OrderCommodity> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<OrderCommodity> skuList) {
        this.skuList = skuList;
    }

    public static class SkuListBean {
        /**
         * id : 164
         * orderId : 24102
         * commodityId : 5195
         * commodityMetaValue : sku
         * commodityName : GOLDEN GOOSEXX女休闲鞋
         * commoditySellPrice : 1540.0
         * commodityListPrice : 2890.0
         * commodityFoldPrice : null
         * commodityNumbers : 1
         * commoditySpec : 无色 36
         * commodityCode : GC0WS590 E37
         * commodityBrand : GOLDEN GOOSE
         * commodityPicturePath : https://smjdev.1magway.com/pictures/platform/commodity/picture/190010902100225504/1.png
         * productId : 2817
         * productType : 0
         * couponsId : 0
         * refundStatus : null
         * refundId : null
         */

        private int id;
        private int orderId;
        private int commodityId;
        private String commodityMetaValue;
        private String commodityName;
        private double commoditySellPrice;
        private double commodityListPrice;
        private Object commodityFoldPrice;
        private int commodityNumbers;
        private String commoditySpec;
        private String commodityCode;
        private String commodityBrand;
        private String commodityPicturePath;
        private int productId;
        private String productType;
        private int couponsId;
        private int refundStatus;
        private int refundId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityMetaValue() {
            return commodityMetaValue;
        }

        public void setCommodityMetaValue(String commodityMetaValue) {
            this.commodityMetaValue = commodityMetaValue;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public double getCommoditySellPrice() {
            return commoditySellPrice;
        }

        public void setCommoditySellPrice(double commoditySellPrice) {
            this.commoditySellPrice = commoditySellPrice;
        }

        public double getCommodityListPrice() {
            return commodityListPrice;
        }

        public void setCommodityListPrice(double commodityListPrice) {
            this.commodityListPrice = commodityListPrice;
        }

        public Object getCommodityFoldPrice() {
            return commodityFoldPrice;
        }

        public void setCommodityFoldPrice(Object commodityFoldPrice) {
            this.commodityFoldPrice = commodityFoldPrice;
        }

        public int getCommodityNumbers() {
            return commodityNumbers;
        }

        public void setCommodityNumbers(int commodityNumbers) {
            this.commodityNumbers = commodityNumbers;
        }

        public String getCommoditySpec() {
            return commoditySpec;
        }

        public void setCommoditySpec(String commoditySpec) {
            this.commoditySpec = commoditySpec;
        }

        public String getCommodityCode() {
            return commodityCode;
        }

        public void setCommodityCode(String commodityCode) {
            this.commodityCode = commodityCode;
        }

        public String getCommodityBrand() {
            return commodityBrand;
        }

        public void setCommodityBrand(String commodityBrand) {
            this.commodityBrand = commodityBrand;
        }

        public String getCommodityPicturePath() {
            return commodityPicturePath;
        }

        public void setCommodityPicturePath(String commodityPicturePath) {
            this.commodityPicturePath = commodityPicturePath;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public int getCouponsId() {
            return couponsId;
        }

        public void setCouponsId(int couponsId) {
            this.couponsId = couponsId;
        }

        public int getRefundStatus() {
            return refundStatus;
        }

        public void setRefundStatus(int refundStatus) {
            this.refundStatus = refundStatus;
        }

        public int getRefundId() {
            return refundId;
        }

        public void setRefundId(int refundId) {
            this.refundId = refundId;
        }
    }
}
