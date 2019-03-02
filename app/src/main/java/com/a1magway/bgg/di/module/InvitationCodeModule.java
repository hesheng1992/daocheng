package com.a1magway.bgg.di.module;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IGetInvitationCodeData;
import com.a1magway.bgg.data.repository.NetGetInvitationCodeData;
import com.a1magway.bgg.v.personal.IInvitationCodeV;

import dagger.Module;
import dagger.Provides;

/**
 * Created by enid on 2018/6/20.
 */
@Module
public class InvitationCodeModule {

    private IInvitationCodeV mIInvitationCodeV;

    public InvitationCodeModule(IInvitationCodeV mIInvitationCodeV) {
        this.mIInvitationCodeV = mIInvitationCodeV;
    }

    @Provides
    public IInvitationCodeV provideInvitationCodeV(){
        return mIInvitationCodeV;
    }

    @Provides
    public IGetInvitationCodeData provideGetInvitationCodeData(APIManager apiManager) {
        return new NetGetInvitationCodeData(apiManager);
    }
}
