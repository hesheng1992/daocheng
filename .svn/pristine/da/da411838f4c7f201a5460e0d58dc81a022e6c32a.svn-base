package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.AccountUserData;
import com.a1magway.bgg.data.entity.BankCardData;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/6.
 */

public interface IInsertBankCard {
    /**
     * 绑定银行卡
     *
     * @param userid
     * @param realname
     * @param bank_name
     * @param idNumber
     * @param accountNumber
     * @param accountType
     * @param phoneNumber
     * @return
     */
    public Observable<APIResponse> insertBankCard(int userid,
                                                  String realname,
                                                  String bank_name,
                                                  String idNumber,
                                                  String accountNumber,
                                                  String accountType,
                                                  String abbreviation,
                                                  String phoneNumber,
                                                  String verification,
                                                  String sessionId);


    /**
     * 银行卡前六位查询银行卡归属信息
     *
     * @param id 银行卡号前六位
     * @return
     */
    public  Observable<BankCardData> queryCardBin(String id);


    /**
     * 使用完整银行卡号查询银行卡信息
     *
     * @param account_number
     * @return
     */
    public Observable<BankCardData> queryCardBinFull(String account_number);

    /**
     * 绑定银行卡发送验证码
     * @param phone
     * @return
     */
    public Observable<String> sendVerification(String phone);

    /**
     * 绑定银行卡之前获取用户数据
     * @param userid
     * @return
     */
    public Observable<AccountUserData> userInfo(int userid);
}
