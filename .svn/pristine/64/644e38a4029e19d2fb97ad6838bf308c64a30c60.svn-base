package com.a1magway.bgg.di.module.member;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IMemberUpgradeInfoData;
import com.a1magway.bgg.data.repository.NetMemberUpgradeInfoData;
import com.a1magway.bgg.v.member.MemberUpgradeInfoContract;
import dagger.Module;
import dagger.Provides;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * author: Beaven
 * date: 2017/10/13 14:29
 */
@Module
public class MemberUpgradeInfoModule {

    private MemberUpgradeInfoContract.View memberUpgradeInfoV;


    public MemberUpgradeInfoModule(MemberUpgradeInfoContract.View memberUpgradeInfoV) {
        this.memberUpgradeInfoV = memberUpgradeInfoV;
    }


    @Provides
    public MemberUpgradeInfoContract.View provideMemberUpgradeInfo() {
        return memberUpgradeInfoV;
    }


    @Provides
    public IMemberUpgradeInfoData provideMemberUpgradeInfoData(APIManager apiManager) {
        return new NetMemberUpgradeInfoData(apiManager);
    }


    @Provides
    public MultiTypeAdapter provideAdapter() {
        return new MultiTypeAdapter();
    }
}
