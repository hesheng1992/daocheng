package com.a1magway.bgg.di.module.mainhome;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IMainHomeData;
import com.a1magway.bgg.data.repository.NetMainHomeData;
import com.a1magway.bgg.v.mainhome.IMainHomeV;

import dagger.Module;
import dagger.Provides;

/**
 * Created by enid on 2018/6/13.
 */
@Module
public class MainHomeModule {
    IMainHomeV iMainHomeV;

    public MainHomeModule(IMainHomeV iMainHomeV) {
        this.iMainHomeV = iMainHomeV;
    }

    @Provides
    public IMainHomeV provideMainHomeView(){
        return iMainHomeV;
    }

    @Provides
    public IMainHomeData provideMainHomeData(APIManager apiManager){
        return new NetMainHomeData(apiManager);
    }
}
