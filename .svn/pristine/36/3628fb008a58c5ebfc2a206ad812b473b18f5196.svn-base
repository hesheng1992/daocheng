package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.AccountWalletData;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/12.
 */

public class NetAccountData implements IAccountData {
    APIManager apiManager;

    public NetAccountData(APIManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public Observable<AccountWalletData> getUserAccount(int userId,int lastId) {
        return apiManager.getUserAccount(userId,lastId);
    }
}
