package com.a1magway.bgg.di.module;

import com.a1magway.bgg.v.articleManager.AticleManagerContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by enid on 2018/8/22.
 */

@Module
public class AticleManagerModule {
    private AticleManagerContract.View view;
    public AticleManagerModule(AticleManagerContract.View view){
        this.view = view;
    }

    @Provides
    public AticleManagerContract.View provideAticleManagerContractView(){
        return view;
    }
}
