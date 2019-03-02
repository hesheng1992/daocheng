package com.a1magway.bgg.di.module.member;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.NetUserData;
import com.a1magway.bgg.v.member.MemberCardsContract;
import dagger.Module;
import dagger.Provides;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * author: Beaven
 * date: 2017/10/20 11:27
 */
@Module
public class MemberCardsModule {

    private MemberCardsContract.View memberCardV;


    public MemberCardsModule(MemberCardsContract.View memberCardV) {
        this.memberCardV = memberCardV;
    }


    @Provides
    public MemberCardsContract.View provideMemberCardV() {
        return memberCardV;
    }


    @Provides
    public MultiTypeAdapter provideAdapter() {
        return new MultiTypeAdapter();
    }


    @Provides
    public NetUserData provideUserData(APIManager apiManager) {
        return new NetUserData(apiManager);
    }
}
