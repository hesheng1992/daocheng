package com.a1magway.bgg.di.component.account;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.account.AccountManageModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.account.AccountManageActivity;

import dagger.Component;

/**
 * Created by enid on 2018/6/7.
 */
@PerActivity
@Component(modules = AccountManageModule.class,dependencies = AppComponent.class)
public interface AccountManageComponent {
    void inject(AccountManageActivity activity);
}
