package com.a1magway.bgg.data.entity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.a1magway.bgg.data.entity.OrderType.ALL;
import static com.a1magway.bgg.data.entity.OrderType.COMPLETED;
import static com.a1magway.bgg.data.entity.OrderType.DELIVERED;
import static com.a1magway.bgg.data.entity.OrderType.WAIT_DELIVER;
import static com.a1magway.bgg.data.entity.OrderType.WAIT_PAY;

/**
 * Created by jph on 2017/8/16.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({ALL, WAIT_PAY, WAIT_DELIVER, DELIVERED, COMPLETED})
public @interface OrderType {
    int WAIT_PAY = 0;//待支付
    int WAIT_DELIVER = 1;//待发货，已付款
    int DELIVERED = 2;//已发货，待收货
    int COMPLETED = 3;//已完成
    int ALL = 4;//全部
}
