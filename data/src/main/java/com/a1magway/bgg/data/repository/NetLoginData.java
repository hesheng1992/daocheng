package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;


/**
 * 登录data
 * Created by lyx on 2017/7/31.
 */
public class NetLoginData implements ILoginData{
    APIManager mAPIManager;

    public NetLoginData(APIManager APIManager) {
        mAPIManager = APIManager;
    }
    @Override
    public Observable<LoginData> login(String phone, String pwd) {
        return mAPIManager.login(phone, pwd);
    }
}
