package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.BankCardData;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/8.
 */

public class NetAccountWithdraw implements IAccountWithdraw {
    private APIManager apiManager;

    public NetAccountWithdraw(APIManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public Observable<APIResponse> withdrawal(int userId, String money, String remark, String accountNumber, String accountType) {
        return apiManager.withdrawal(userId,money,remark,accountNumber,accountType);
    }

    @Override
    public Observable<BankCardData> queryCardBinFull(String account_number) {
        return apiManager.queryCardBinFull(account_number);
    }
}
