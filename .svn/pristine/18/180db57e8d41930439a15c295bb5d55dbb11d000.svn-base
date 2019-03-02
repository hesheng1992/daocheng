package com.a1magway.bgg.di.module;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IInvitationData;
import com.a1magway.bgg.data.repository.NetInvitationData;
import com.a1magway.bgg.v.invitation.InvitationContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by enid on 2018/5/29.
 */

@Module
public class InvitationModule {
    private InvitationContract.View mIInvitationV;

    public InvitationModule(InvitationContract.View mIRegisterV) {
        this.mIInvitationV = mIRegisterV;
    }

    @Provides
    public InvitationContract.View provideInvitationV(){
        return mIInvitationV;
    }

    @Provides
    public IInvitationData provideInvitationData(APIManager apiManager){
        return new NetInvitationData(apiManager);
    }
}
