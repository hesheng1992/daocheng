package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.PintuanModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.pintuan.PingtuanActivity;

import dagger.Component;

/**
 * Created by enid on 2018/8/22.
 */

@PerActivity
@Component(modules = PintuanModule.class,dependencies = AppComponent.class)
public interface PingtuanComponent {
    void inject(PingtuanActivity pintuanActivity);
}
