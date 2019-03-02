package com.a1magway.bgg.di.module;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IHuanxinData;
import com.a1magway.bgg.data.repository.NetHuanxinData;
import com.a1magway.bgg.p.personal.PersonalFeatureAdapter;
import com.a1magway.bgg.v.personal.IPersonalV;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lyx on 2017/8/1.
 */
@Module
public class PersonalModule {

    IPersonalV mIPersonalV;

    public PersonalModule(IPersonalV iPersonalV) {
        mIPersonalV = iPersonalV;
    }

    @Provides
    public IPersonalV providePersonalV() {
        return mIPersonalV;
    }

//    @Provides
//    public APIManager provideAPIManager(APIManager apiManager) {
//        return apiManager;
//    }

    @Provides
    public PersonalFeatureAdapter providePersonalFeatureAdapter() {
        return new PersonalFeatureAdapter();
    }

    @Provides
    public IHuanxinData provideHuanxinData(APIManager apiManager){
        return new NetHuanxinData(apiManager);
    }
}
