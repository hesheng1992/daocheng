package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.MemberRegisterValue;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * author: Beaven
 * date: 2017/10/17 16:15
 */

public class NetMemberRegisterInfoData implements IMemberRegisterInfoData {
    private APIManager apiManager;

    public NetMemberRegisterInfoData(APIManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public Observable<Object> registerMemberInfo(MemberRegisterValue registerValue) {
        return apiManager.registerMemberInfo(registerValue);
    }
}
