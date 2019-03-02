package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.HomeModule;
import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.v.home.HomeFragment;

import dagger.Component;

/**
 * Created by jph on 2017/7/24.
 */
@PerFragment
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {

    void inject(HomeFragment homeFragment);
}
