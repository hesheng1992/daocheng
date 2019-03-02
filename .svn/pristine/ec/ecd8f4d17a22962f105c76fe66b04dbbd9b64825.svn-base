package com.a1magway.bgg.data.entity;

import java.io.Serializable;

/**
 * 预设的筛选条件
 * Created by jph on 2017/8/4.
 */
public class PresetFilterRule implements Serializable {
    private static final long serialVersionUID = 6742262499387140436L;
    private boolean valid = false;//是否有效

    private int categoryId = -1;//分类id
    private String categoryFlag;//可为空	分类标识 （大分类1；小分类：0）
    private int brandId;
    private String brandName;

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

    public void setCate1Id(int cate1Id) {
        setCategoryFlag("1");
        setCategoryId(cate1Id);
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}

