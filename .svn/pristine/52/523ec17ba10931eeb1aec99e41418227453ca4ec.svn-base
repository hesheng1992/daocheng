package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * author: Beaven
 * date: 2017/10/18 16:15
 */

public class NetUserData implements IUserData {

    private APIManager apiManager;

    public NetUserData(APIManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public Observable<LoginData> getUserInfo(String userId) {
        return apiManager.getUserInfo(userId);
    }
}
