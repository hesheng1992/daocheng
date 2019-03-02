package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.MainModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.main.MainActivity;

import dagger.Component;

/**
 * 对应主体框架也
 * Created by jph on 2017/7/20.
 */
@PerActivity
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {

    void inject(MainActivity mainActivity);

//    CartComponent getCartComponent(CartModule cartModule);
}
