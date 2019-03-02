package com.a1magway.bgg.di.module;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.personalcenterdata.IPersonalSettingData;
import com.a1magway.bgg.data.repository.personalcenterdata.NetPersonalSettingData;
import com.a1magway.bgg.v.personal.IPersonalSettingV;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lyx on 2017/8/8.
 */
@Module
public class PersonalSettingModule {

    IPersonalSettingV mIPersonalSettingV;

    public PersonalSettingModule(IPersonalSettingV iPersonalSettingV){
        mIPersonalSettingV = iPersonalSettingV;
    }

    @Provides
    public IPersonalSettingV providerIPersonalSettingV(){
        return mIPersonalSettingV;
    }

    @Provides
    public IPersonalSettingData providerIPersonalSettingData(APIManager apiManager){
        return new NetPersonalSettingData(apiManager);
    }
}
