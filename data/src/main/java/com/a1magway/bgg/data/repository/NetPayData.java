package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.OrderAliPay;
import com.a1magway.bgg.data.entity.WXPayInfo;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by jph on 2017/8/22.
 */
public class NetPayData implements IPayData {

    private APIManager mAPIManager;

    public NetPayData(APIManager APIManager) {
        mAPIManager = APIManager;
    }

    @Override
    public Observable<OrderAliPay> getPreAliPayData(String orderNum) {
        return mAPIManager.getPreAliPayData(orderNum);
    }

    @Override
    public Observable<String> checkAliPayOrderResult(String resultStatus, String result) {
        return mAPIManager.checkAliPayOrderResult(resultStatus, result);
    }

    @Override
    public Observable<String> checkAliPayMemberResult(String resultStatus, String result) {
        return mAPIManager.checkAliPayMemberResult(resultStatus, result);
    }

    @Override
    public Observable<WXPayInfo> getPreWXPayData(String orderNum) {
        return mAPIManager.getPreWXPayData(orderNum);
    }
}
