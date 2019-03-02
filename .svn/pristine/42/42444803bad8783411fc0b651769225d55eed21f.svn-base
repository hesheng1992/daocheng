package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/5/29.
 */

public class NetThirdpartLoginData implements IThirdpartLoginData {
    private APIManager mApiManager;

    public NetThirdpartLoginData(APIManager mApiManager) {
        this.mApiManager = mApiManager;
    }


    @Override
    public Observable<LoginData> thirdPartLogin(String open_id) {
        return mApiManager.thirdpartiesLogin(open_id);
    }
}
