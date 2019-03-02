package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.OrderItem;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by jph on 2017/8/17.
 */
public interface IOrderListData {

    /**
     * 获取订单列表
     *
     * @param userId
     * @param type
     * @param lastOrderId 分页
     * @return
     */
    Observable<List<OrderItem>> getOrderList(int userId, int type, int lastOrderId);

    Observable<String> cancelOrder(int userId, int orderId);

    Observable<String> deleteOrder(int userId, int orderId);

    Observable<APIResponse> confirmFinish(int orderId);
}
