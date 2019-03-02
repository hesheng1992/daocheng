package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.MemberRegisterGift;
import com.a1magway.bgg.data.entity.MemberUpgradeInfo;
import com.a1magway.bgg.data.net.APIManager;

import java.util.List;

import io.reactivex.Observable;

/**
 * author: Beaven
 * date: 2017/10/13 14:25
 */

public class NetMemberUpgradeInfoData implements IMemberUpgradeInfoData {
    private APIManager apiManager;

    public NetMemberUpgradeInfoData(APIManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public Observable<MemberUpgradeInfo> getMemberUpgradeInfo() {
        return apiManager.getMemberUpgradeInfo();
    }

    @Override
    public Observable<List<MemberRegisterGift>> getMemberRegisterGift() {
        return apiManager.getMemberRegisterGift();
    }
}
