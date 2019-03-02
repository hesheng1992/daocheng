package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.OrderDetails;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by jph on 2017/8/19.
 */
public class NetOrderDetailsData implements IOrderDetailsData {

    private APIManager mAPIManager;

    public NetOrderDetailsData(APIManager APIManager) {
        mAPIManager = APIManager;
    }

    @Override
    public Observable<OrderDetails> getOrderDetails(int userId, String orderNumber) {
        return mAPIManager.getOrderDetails(userId, orderNumber);
    }

    @Override
    public Observable<OrderDetails> getSaleOrderDetails(int userId, int creator_id, String orderNumber) {
        return mAPIManager.getSaleOrderDetails(userId, creator_id, orderNumber);
    }
}
