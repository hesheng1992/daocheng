package com.a1magway.bgg.di.module;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IModifyPwdData;
import com.a1magway.bgg.data.repository.NetModifyPwdData;
import com.a1magway.bgg.v.personal.IModifyPwdV;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lyx on 2017/8/8.
 */
@Module
public class ModifyPwdModule {

    IModifyPwdV mIModifyPwdV;

    public ModifyPwdModule(IModifyPwdV iModifyPwdV){
        this.mIModifyPwdV = iModifyPwdV;
    }

    @Provides
    public IModifyPwdV providerIModifyPwdV(){
        return  this.mIModifyPwdV;
    }

    @Provides
    public IModifyPwdData providerIModifyPwdData(APIManager apiManager){
        return new NetModifyPwdData(apiManager);
    }
}
