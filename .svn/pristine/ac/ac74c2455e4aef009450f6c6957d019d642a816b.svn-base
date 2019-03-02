package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.data.net.APIManager;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by jph on 2017/8/17.
 */
public class NetOrderListData implements IOrderListData {

    private APIManager mAPIManager;

    public NetOrderListData(APIManager APIManager) {
        mAPIManager = APIManager;
    }

    @Override
    public Observable<List<OrderItem>> getOrderList(int userId, int type, int lastOrderId) {
        return mAPIManager.getOrderList(userId, type, lastOrderId);
    }

    @Override
    public Observable<String> cancelOrder(int userId, int orderId) {
        return mAPIManager.cancelOrder(userId, orderId);
    }

    @Override
    public Observable<String> deleteOrder(int userId, int orderId) {
        return mAPIManager.deleteOrder(userId, orderId);
    }

    @Override
    public Observable<APIResponse> confirmFinish(int orderId) {
        return mAPIManager.confirmFinish(orderId);
    }
}
