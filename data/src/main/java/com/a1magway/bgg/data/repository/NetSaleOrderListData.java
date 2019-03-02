package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.data.net.APIManager;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by jph on 2017/8/17.
 */
public class NetSaleOrderListData implements ISaleOrderListData {

    private APIManager mAPIManager;

    public NetSaleOrderListData(APIManager APIManager) {
        mAPIManager = APIManager;
    }

    @Override
    public Observable<List<OrderItem>> getSaleOrderList(int type, int lastOrderId) {
        return mAPIManager.getSaleOrderList(type,lastOrderId);
    }
}
