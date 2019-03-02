package com.a1magway.bgg.di.component.mainhome;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.mainhome.MainHomeModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.mainhome.MainHomeFragment;

import dagger.Component;

/**
 * Created by enid on 2018/6/13.
 */
@PerActivity
@Component(modules = MainHomeModule.class,dependencies = AppComponent.class)
public interface MainHomeComponent {
    void inject(MainHomeFragment fragment);
}
