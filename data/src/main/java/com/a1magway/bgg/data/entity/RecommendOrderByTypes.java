package com.a1magway.bgg.data.entity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 排序方式
 * Created by jph on 2017/8/2.
 */
public class RecommendOrderByTypes {

    public static final int COMPREHENSIVE = 11;//综合
    public static final int SALE_COUNT_ASC = 21;//销量升序
    public static final int SALE_COUNT_DESC = 22;//销量降序
    public static final int PRICE_ASC = 31;//价格升序
    public static final int PRICE_DESC = 32;//价格降序
    public static final int DISCOUNT_ASC = 41;//折扣升序
    public static final int DISCOUNT_DESC = 42;//折扣降序


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({COMPREHENSIVE,SALE_COUNT_DESC, PRICE_ASC, PRICE_DESC, DISCOUNT_ASC, DISCOUNT_DESC,SALE_COUNT_ASC})
    public @interface RecommendOrderByType {
    }
}
