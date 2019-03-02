package com.a1magway.bgg.di.module.member;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IMemberUpgradeInfoData;
import com.a1magway.bgg.data.repository.NetMemberRegisterInfoData;
import com.a1magway.bgg.data.repository.NetMemberUpgradeInfoData;
import com.a1magway.bgg.data.repository.NetUserData;
import com.a1magway.bgg.data.repository.personalcenterdata.IPersonalSettingData;
import com.a1magway.bgg.data.repository.personalcenterdata.NetPersonalSettingData;
import com.a1magway.bgg.v.member.MemberRegisterContract;
import dagger.Module;
import dagger.Provides;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * author: Beaven
 * date: 2017/10/16 14:33
 */
@Module
public class MemberRegisterModule {

    private MemberRegisterContract.View memberRegisterV;

    public MemberRegisterModule(MemberRegisterContract.View memberRegisterV) {
        this.memberRegisterV = memberRegisterV;
    }

    @Provides
    public MemberRegisterContract.View provideMemberRegister() {
        return memberRegisterV;
    }

    @Provides
    public IMemberUpgradeInfoData provideMemberUpgradeInfoData(APIManager apiManager) {
        return new NetMemberUpgradeInfoData(apiManager);
    }

    @Provides
    public IPersonalSettingData providerIPersonalSettingData(APIManager apiManager) {
        return new NetPersonalSettingData(apiManager);
    }

    @Provides
    public NetMemberRegisterInfoData provideMemberRegisterInfoData(APIManager apiManager){
        return new NetMemberRegisterInfoData(apiManager);
    }

    @Provides
    public NetUserData provideUserData(APIManager apiManager){
        return new NetUserData(apiManager);
    }

    @Provides
    public MultiTypeAdapter provideAdapter() {
        return new MultiTypeAdapter();
    }

    @Provides
    public Items provideItems() {
        return new Items();
    }
}
