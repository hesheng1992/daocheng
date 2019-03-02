package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.OrderDetails;

import io.reactivex.Observable;

/**
 * Created by jph on 2017/8/19.
 */
public interface IOrderDetailsData {

    Observable<OrderDetails> getOrderDetails(int userId, String orderNumber);

    Observable<OrderDetails> getSaleOrderDetails(int userId,int user_id, String orderNumber);
}
