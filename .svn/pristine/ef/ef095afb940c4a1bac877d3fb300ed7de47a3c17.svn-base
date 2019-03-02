package com.a1magway.bgg.di.module;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.ILoginData;
import com.a1magway.bgg.data.repository.ISetPwdData;
import com.a1magway.bgg.data.repository.NetLoginData;
import com.a1magway.bgg.data.repository.NetSetPwdData;
import com.a1magway.bgg.v.login.PasswordResetContract;
import dagger.Module;
import dagger.Provides;

/**
 * 设置密码
 * Created by lyx on 2017/8/7.
 */
@Module
public class SetPasswordModule {
    private PasswordResetContract.View mISetPassWordV;


    public SetPasswordModule(PasswordResetContract.View iSetPassWordV) {
        mISetPassWordV = iSetPassWordV;
    }


    @Provides
    public PasswordResetContract.View providerISetPassWordV() {
        return mISetPassWordV;
    }


    @Provides
    public ISetPwdData providerISetPwdData(APIManager apiManager) {
        return new NetSetPwdData(apiManager);
    }


    @Provides
    public ILoginData providerILoginData(APIManager apiManager) {
        return new NetLoginData(apiManager);
    }
}
