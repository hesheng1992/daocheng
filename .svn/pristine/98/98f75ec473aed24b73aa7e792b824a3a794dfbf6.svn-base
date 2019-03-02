package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by lyx on 2017/8/8.
 */
public class NetModifyPwdData implements IModifyPwdData {

    APIManager mAPIManager;

    public NetModifyPwdData(APIManager apiManager){
        this.mAPIManager = apiManager;

    }

    @Override
    public Observable<APIResponse> changePassword(String phone, String oldPwd, String pwd) {
        return mAPIManager.changePassword(phone, oldPwd, pwd);
    }
}
