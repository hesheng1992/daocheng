package com.a1magway.bgg.di.module;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.ISendVerificationData;
import com.a1magway.bgg.data.repository.NetSendVerifitionCodeData;
import com.a1magway.bgg.v.login.UserVerificationContract;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lyx on 2017/8/1.
 */
@Module
public class UserVerificationModule {

    private UserVerificationContract.View mISendVerificationCodeV;


    public UserVerificationModule(UserVerificationContract.View iSendVerificationCodeV) {
        mISendVerificationCodeV = iSendVerificationCodeV;
    }


    @Provides
    public UserVerificationContract.View provideSendVerificationCodeV() {
        return mISendVerificationCodeV;
    }


    @Provides
    public ISendVerificationData provideData(APIManager apiManager) {
        return new NetSendVerifitionCodeData(apiManager);
    }

}
