package com.a1magway.bgg.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 商品Model
 * Created by jph on 2017/8/7.
 */

public class Product implements Serializable {
    /**
     * id : 1424
     * isOverSea : 1
     * skuList : [{"id":3182,"skuId":3182,"productName":"CALVIN KLEIN POLO衫 & T恤 蓝 K10K100970JASESH478","productId":1424,"brand":"CALVIN KLEIN","listPrice":"1051.98","sellPrice":"788.00","totalStock":29162,"saleRate":"25%OFF","skuCover":["https://www.1magway.shop:18085/pictures/platform/commodity/picture/201705/1495782806620_917498096.jpg","https://www.1magway.shop:18085/pictures/platform/commodity/picture/201705/1495782811406_919345138.jpg","https://www.1magway.shop:18085/pictures/platform/commodity/picture/201705/1495782811544_921192180.jpg","https://www.1magway.shop:18085/pictures/platform/commodity/picture/201705/1495782811549_942433163.jpg"],"color":"蓝色","measures":"S","style":null,"volume":null,"specs":"颜色:蓝色 尺寸:S","specsMap":{"颜色":"蓝色","尺寸":"S"},"count":null,"productCode":"K10K100970JASESH478","tax":"0.00","freight":"￥180","valiInfo":null,"valid":true}]
     * brand : CALVIN KLEIN
     * title : CALVIN KLEIN POLO衫 & T恤 蓝 K10K100970JASESH478
     * overSeaInfo : 海外仓：海外仓位于米兰，消费者下单后通过联邦快递欧洲直邮约需要15-20天抵达国内
     * seckill : 0
     * startTime : 1495728000000
     * endTime : 1558800000000
     * sizePath : https://www.1magway.shop:18085/pictures/platform/commodity/picture/html/sizeInfo/size.html
     * bigPicList : ["https://www.1magway.shop:18085/pictures/platform/commodity/picture/201705/45611495782713498.jpg","https://www.1magway.shop:18085/pictures/platform/commodity/picture/201705/18831495782713634.jpg","https://www.1magway.shop:18085/pictures/platform/commodity/picture/201705/50771495782713887.jpg","https://www.1magway.shop:18085/pictures/platform/commodity/picture/201705/81981495782713968.jpg","https://www.1magway.shop:18085/pictures/platform/commodity/picture/201705/60551495782714083.jpg","https://www.1magway.shop:18085/pictures/platform/commodity/picture/201705/15681495782714216.jpg"]
     */

    private int id = 0;
    private int isOverSea = 0;//是否海外仓
    private String brand = "";
    private String title = "";
    private String overSeaInfo = "";//海外仓信息
    private int seckill = 0;
    private long startTime = 0L;//秒杀开始时间
    private long endTime = 0L;//秒杀结束时间
    private String productType = "";
    private String sizePath = "";
    private List<Commodity> skuList;
    private List<String> bigPicList;
    private String favoriteId = "";//关注id
    private String briefin;
    private int isCollage; //是否拼团 0.否 1.是
    private long collageEndTime;//拼团结束时间
    private int couponsNum;//当前发起拼团的人数

    public int getCouponsNum() {
        return couponsNum;
    }

    public void setCouponsNum(int couponsNum) {
        this.couponsNum = couponsNum;
    }

    public int getIsCollage() {
        return isCollage;
    }

    public void setIsCollage(int isCollage) {
        this.isCollage = isCollage;
    }

    public long getCollageEndTime() {
        return collageEndTime;
    }

    public void setCollageEndTime(long collageEndTime) {
        this.collageEndTime = collageEndTime;
    }

    public String getBriefin() {
        return briefin;
    }

    public void setBriefin(String briefin) {
        this.briefin = briefin;
    }

    public String getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(String favoriteId) {
        this.favoriteId = favoriteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsOverSea() {
        return isOverSea;
    }

    public void setIsOverSea(int isOverSea) {
        this.isOverSea = isOverSea;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverSeaInfo() {
        return overSeaInfo;
    }

    public void setOverSeaInfo(String overSeaInfo) {
        this.overSeaInfo = overSeaInfo;
    }

    public int getSeckill() {
        return seckill;
    }

    public void setSeckill(int seckill) {
        this.seckill = seckill;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getSizePath() {
        return sizePath;
    }

    public void setSizePath(String sizePath) {
        this.sizePath = sizePath;
    }

    public List<Commodity> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Commodity> skuList) {
        this.skuList = skuList;
    }

    public List<String> getBigPicList() {
        return bigPicList;
    }

    public void setBigPicList(List<String> bigPicList) {
        this.bigPicList = bigPicList;
    }

    public boolean isOverSea() {
        return getIsOverSea() == 1;
    }

    public boolean isSeckill() {
        return seckill == 1;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
