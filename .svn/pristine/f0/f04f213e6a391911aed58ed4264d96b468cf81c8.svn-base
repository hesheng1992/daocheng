package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.HuanXinLoginInfo;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/4.
 */

public class NetHuanxinData implements IHuanxinData {
    private APIManager mApiManager;

    public NetHuanxinData(APIManager mApiManager) {
        this.mApiManager = mApiManager;
    }

    @Override
    public Observable<HuanXinLoginInfo> hxLoginInfoGet() {
        return mApiManager.hxLoginInfoGet();
    }

    @Override
    public Observable<APIResponse> hxLoginInfoRelease(int id) {
        return mApiManager.hxLoginInfoRelease(id);
    }
}
