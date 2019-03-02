package com.a1magway.bgg.data.entity;

import java.io.Serializable;

/**
 * 商品列表筛选规则
 * Created by jph on 2017/7/31.
 */
public class ProductFilterRule implements Serializable {

    private static final long serialVersionUID = 2192956218538913750L;

    private String buyOrder;//可为空	销量排序（默认asc）倒叙desc 三种排序必传一个
    private String discountOrder;//可为空	打折排序
    private String priceOrder;//可为空	价格排序
    private String generalOrder; //综合排序
    private int brandId = -1;//可为空	品牌id
    private int categoryId = -1;//分类id
    private String categoryFlag;//可为空	分类标识 （大分类1；小分类：0）
    private String minPrice;//最低价
    private String maxPrice;//最高价
    private String filterSex;//用于筛选分类男女标识（女0；男1）

    public ProductFilterRule() {
//        setGeneralOrder("asc");
        setGeneralOrder("desc");
        setBrandId(-1);
        setCategoryId(-1);
    }

    public String getGeneralorder() {
        return generalOrder;
    }

    public void setGeneralOrder(String generalOrder) {
        this.generalOrder = generalOrder;
    }

    public String getBuyOrder() {
        return buyOrder;
    }

    public void setBuyOrder(String buyOrder) {
        this.buyOrder = buyOrder;
    }

    public String getDiscountOrder() {
        return discountOrder;
    }

    public void setDiscountOrder(String discountOrder) {
        this.discountOrder = discountOrder;
    }

    public String getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(String priceOrder) {
        this.priceOrder = priceOrder;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryFlag() {
        return categoryFlag;
    }

    public void setCategoryFlag(String categoryFlag) {
        this.categoryFlag = categoryFlag;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getFilterSex() {
        return filterSex;
    }

    public void setFilterSex(String filterSex) {
        this.filterSex = filterSex;
    }

    public void setOrderByType(@OrderByTypes.OrderByType int orderByType) {
        switch (orderByType) {
            case OrderByTypes.SALE_COUNT_ASC:
                setBuyOrder("asc");
                setPriceOrder(null);
                setDiscountOrder(null);
                setGeneralOrder(null);
                break;
            case OrderByTypes.PRICE_ASC:
                setBuyOrder(null);
                setPriceOrder("asc");
                setDiscountOrder(null);
                setGeneralOrder(null);
                break;
            case OrderByTypes.PRICE_DESC:
                setBuyOrder(null);
                setPriceOrder("desc");
                setDiscountOrder(null);
                setGeneralOrder(null);
                break;
            case OrderByTypes.DISCOUNT_ASC:
                setBuyOrder(null);
                setPriceOrder(null);
                setDiscountOrder("asc");
                setGeneralOrder(null);
                break;
            case OrderByTypes.DISCOUNT_DESC:
                setBuyOrder(null);
                setPriceOrder(null);
                setDiscountOrder("desc");
                setGeneralOrder(null);
                break;
            case OrderByTypes.COMPREHENSIVE_ASC:
                setBuyOrder(null);
                setPriceOrder(null);
                setDiscountOrder(null);
//                setGeneralOrder("asc");
                setGeneralOrder("desc");
                break;
            case OrderByTypes.SALE_COUNT_DESC:
                setBuyOrder("desc");
                setPriceOrder(null);
                setDiscountOrder(null);
                setGeneralOrder(null);
                break;

        }
    }
}
