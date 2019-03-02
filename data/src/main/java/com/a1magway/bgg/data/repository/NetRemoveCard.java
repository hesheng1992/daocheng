package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/6.
 */

public class NetRemoveCard implements IRemoveCard {
    APIManager mApiManager;

    public NetRemoveCard(APIManager mApiManager) {
        this.mApiManager = mApiManager;
    }

    @Override
    public Observable<APIResponse> removeCard(int id) {
        return mApiManager.removeCard(id);
    }
}
