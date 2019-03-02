package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.MemberRegisterGift;
import com.a1magway.bgg.data.entity.MemberUpgradeInfo;

import java.util.List;

import io.reactivex.Observable;

/**
 * author: Beaven
 * date: 2017/10/13 14:24
 */

public interface IMemberUpgradeInfoData {
    /**
     * 获取会员升级信息
     */
    Observable<MemberUpgradeInfo> getMemberUpgradeInfo();

    /**
     * 获取会员升级礼品
     */
    Observable<List<MemberRegisterGift>> getMemberRegisterGift();
}
