package com.a1magway.bgg.data.entity;

import java.io.Serializable;

/**
 * 选择的分类信息
 * Created by jph on 2017/8/4.
 */
public class SelectCateInfo implements Serializable {
    private static final long serialVersionUID = -5692724459236463904L;

    private FilterCate1 cate1;
    private int gender = -1;
    private FilterCate2 cate2;

    public FilterCate1 getCate1() {
        return cate1;
    }

    public void setCate1(FilterCate1 cate1) {
        this.cate1 = cate1;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public FilterCate2 getCate2() {
        return cate2;
    }

    public void setCate2(FilterCate2 cate2) {
        this.cate2 = cate2;
    }
}
