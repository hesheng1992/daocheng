package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.AppNavigationData;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.net.APIManager;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/13.
 */

public class NetMainHomeData implements IMainHomeData {
    APIManager apiManager;

    public NetMainHomeData(APIManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public Observable<List<AppNavigationData>> getAppNavigation() {
        return apiManager.getAppNavigation();
    }

    @Override
    public Observable<LoginData> thirdPartLogin(String open_id) {
        return apiManager.getUserInfo(open_id);
    }
}
