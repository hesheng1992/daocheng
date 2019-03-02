package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.AccountBankCardData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/6.
 */

public interface IBankCardList {
    public Observable<List<AccountBankCardData>> getBankCardList(int userid);
}
