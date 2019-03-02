package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.AccountBankCardData;
import com.a1magway.bgg.data.net.APIManager;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/6.
 */

public class NetBankCardList implements IBankCardList {
    private APIManager mAPIManager;

    public NetBankCardList(APIManager mAPIManager) {
        this.mAPIManager = mAPIManager;
    }

    @Override
    public Observable<List<AccountBankCardData>> getBankCardList(int userid) {
        return mAPIManager.getBankCardList(userid);
    }
}
