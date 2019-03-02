package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.AccountUserData;
import com.a1magway.bgg.data.entity.BankCardData;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/6.
 */

public class NetInsertBankCard implements IInsertBankCard {
    APIManager mApiManager;

    public NetInsertBankCard(com.a1magway.bgg.data.net.APIManager APIManager) {
        this.mApiManager = APIManager;
    }

    @Override
    public Observable<APIResponse> insertBankCard(int userid,
                                                  String realname,
                                                  String bank_name,
                                                  String idNumber,
                                                  String accountNumber,
                                                  String accountType,
                                                  String abbreviation,
                                                  String phoneNumber,
                                                  String verification,
                                                  String sessionId) {
        return mApiManager.insertBankCard(userid, realname, bank_name, idNumber,
                accountNumber, accountType, abbreviation, phoneNumber, verification,sessionId);
    }

    @Override
    public Observable<BankCardData> queryCardBin(String id) {
        return mApiManager.queryCardBin(id);
    }

    @Override
    public Observable<BankCardData> queryCardBinFull(String account_number) {
        return mApiManager.queryCardBinFull(account_number);
    }

    @Override
    public Observable<String> sendVerification(String phone) {
        return mApiManager.sendVerification(phone);
    }

    @Override
    public Observable<AccountUserData> userInfo(int userid) {
        return mApiManager.userInfo(userid);
    }
}
