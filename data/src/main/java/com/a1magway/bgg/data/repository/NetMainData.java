package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.CartNum;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by jph on 2017/8/25.
 */
public class NetMainData implements IMainData {
    private APIManager mAPIManager;

    public NetMainData(APIManager APIManager) {
        mAPIManager = APIManager;
    }

    @Override
    public Observable<CartNum> getCartNum() {
        return mAPIManager.getCartNum();
    }
}
