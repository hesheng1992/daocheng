package com.a1magway.bgg.di.component.account;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.account.MyWalletModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.account.MyWalletActivity;

import dagger.Component;

/**
 * Created by enid on 2018/6/13.
 */
@PerActivity
@Component(modules = MyWalletModule.class,dependencies = AppComponent.class)
public interface MyWalletComponent {
    void inject(MyWalletActivity activity);
}
