package com.a1magway.bgg.di.component.account;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.account.AccountWalletModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.account.MyWalletActivity;

import dagger.Component;

/**
 * Created by enid on 2018/6/12.
 */
@PerActivity
@Component(modules = AccountWalletModule.class,dependencies = AppComponent.class)
public interface AccountWalletComponent {
    public void inject(MyWalletActivity activity);
}
