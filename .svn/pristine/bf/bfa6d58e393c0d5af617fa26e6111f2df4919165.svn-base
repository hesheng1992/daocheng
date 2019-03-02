package com.a1magway.bgg.data.repository.personalcenterdata;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by lyx on 2017/8/8.
 */
public class NetPersonalSettingData implements IPersonalSettingData {
    APIManager mAPIManager;

    public NetPersonalSettingData(APIManager apiManager){
        this.mAPIManager = apiManager;
    }

    @Override
    public Observable<APIResponse> updateUserInfo(String phone, Integer gender, Long date,String nickname) {
        return mAPIManager.updateUserInfo(phone, gender, date,nickname);
    }
}
