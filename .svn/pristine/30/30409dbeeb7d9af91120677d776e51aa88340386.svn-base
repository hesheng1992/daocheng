package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by lyx on 2017/8/9.
 */
public class NetSendVerifitionCodeData implements ISendVerificationData {
    APIManager mAPIManager;

    public NetSendVerifitionCodeData(APIManager apiManager){
        mAPIManager = apiManager;
    }

    @Override
    public Observable<String> sendVerification(String phone, int type) {
        return mAPIManager.sendVerificationCode(phone, type);
    }

    @Override
    public Observable<APIResponse> checkCode(String phone, String code) {
        return mAPIManager.checkVerificationCode(phone, code);
    }
}
