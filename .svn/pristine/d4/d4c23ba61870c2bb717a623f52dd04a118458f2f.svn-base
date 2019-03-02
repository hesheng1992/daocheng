package com.a1magway.bgg.di.module;

import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.pintuan.PingtuanProductContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by enid on 2018/8/22.
 */
@Module
public class PintuanModule {
    private PingtuanProductContract.View view;

    public PintuanModule(PingtuanProductContract.View view) {
        this.view = view;
    }

    @PerActivity
    @Provides
    public PingtuanProductContract.View providesPingtuanProductContractView() {
        return view;
    }
}
