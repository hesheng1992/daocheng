package com.a1magway.bgg.data.entity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 排序方式
 * Created by jph on 2017/8/2.
 */
public class OrderByTypes {

    public static final int SALE_COUNT_DESC=1; //销量降序
    public static final int SALE_COUNT_ASC = 2;//销量升序
    public static final int PRICE_ASC = 11;//价格升序
    public static final int PRICE_DESC = 12;//价格降序
    public static final int DISCOUNT_ASC = 21;//折扣升序
    public static final int DISCOUNT_DESC = 22;//折扣降序
    public static final int COMPREHENSIVE_DESC=3; //综合排序
    public static final int COMPREHENSIVE_ASC=4; //综合排序

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({SALE_COUNT_ASC, PRICE_ASC, PRICE_DESC, DISCOUNT_ASC, DISCOUNT_DESC,SALE_COUNT_DESC,COMPREHENSIVE_DESC,COMPREHENSIVE_ASC})
    public @interface OrderByType {
    }
}
