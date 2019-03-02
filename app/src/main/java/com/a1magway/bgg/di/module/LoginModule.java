package com.a1magway.bgg.di.module;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.ILoginData;
import com.a1magway.bgg.data.repository.IThirdpartLoginData;
import com.a1magway.bgg.data.repository.IThirdpartRegisterData;
import com.a1magway.bgg.data.repository.NetLoginData;
import com.a1magway.bgg.data.repository.NetThirdpartLoginData;
import com.a1magway.bgg.data.repository.NetThirdpartRegisterData;
import com.a1magway.bgg.v.login.LoginContract;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lyx on 2017/7/31.
 */
@Module
public class LoginModule {
    private LoginContract.View mILoginV;


    public LoginModule(LoginContract.View iLoginV) {
        mILoginV = iLoginV;
    }


    @Provides
    public LoginContract.View provideLoginV() {
        return mILoginV;
    }


    @Provides
    public ILoginData provideLoginData(APIManager apiManager) {
        return new NetLoginData(apiManager);
    }

    @Provides
    public IThirdpartRegisterData getThirdpartRegisterData(APIManager apiManager){
        return new NetThirdpartRegisterData(apiManager);
    }

    @Provides
    public IThirdpartLoginData getThitdpartLoginData(APIManager apiManager){
        return new NetThirdpartLoginData(apiManager);
    }
}
