package com.a1magway.bgg.data.entity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.a1magway.bgg.data.entity.OrderStatus.CANCELED;
import static com.a1magway.bgg.data.entity.OrderStatus.COMPLETED;
import static com.a1magway.bgg.data.entity.OrderStatus.DELIVERED;
import static com.a1magway.bgg.data.entity.OrderStatus.PAY_BACK_START;
import static com.a1magway.bgg.data.entity.OrderStatus.PAY_BACK_SUCCEED;
import static com.a1magway.bgg.data.entity.OrderStatus.WAIT_DELIVER;
import static com.a1magway.bgg.data.entity.OrderStatus.WAIT_PAY;


/**
 * Created by jph on 2017/8/16.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({WAIT_PAY, WAIT_DELIVER, CANCELED, DELIVERED, COMPLETED,PAY_BACK_START,PAY_BACK_SUCCEED})
public @interface OrderStatus {
    int WAIT_PAY = 1;//待支付
    int WAIT_DELIVER = 2;//待发货，已付款
    int CANCELED = 4;//已取消
    int DELIVERED = 6;//已发货
    int COMPLETED = 3;//已完成
    //
    int PAY_BACK_START = 7;//拼团退款中(现在只有拼团)
    int PAY_BACK_SUCCEED = 8;//拼团退款成功(现在只有拼团)

//    1：待支付 2：待发货 6：待收货  3：已完成
}
