package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by lyx on 2017/8/9.
 */
public class NetSetPwdData implements ISetPwdData{

    APIManager mAPIManager;

    public NetSetPwdData(APIManager apiManager){
        mAPIManager = apiManager;
    }

    @Override
    public Observable<String> setPwd(String phone, String pwd, int type) {
        return mAPIManager.setPassWord(phone, pwd, type);
    }
}
