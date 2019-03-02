package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.BankCardData;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/8.
 */

public interface IAccountWithdraw {
    public Observable<APIResponse> withdrawal(int userId,
                                       String money,
                                       String remark,
                                       String accountNumber,
                                       String accountType);

    /**
     * 使用完整银行卡号查询银行卡信息
     *
     * @param account_number
     * @return
     */
    public Observable<BankCardData> queryCardBinFull(String account_number);
}
