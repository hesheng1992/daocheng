package com.a1magway.bgg.di.component.account;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.account.AccountWithdrawModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.account.AccountWithdrawActivity;

import dagger.Component;

/**
 * Created by enid on 2018/6/8.
 */

@PerActivity
@Component(modules = AccountWithdrawModule.class,dependencies = AppComponent.class)
public interface AccountWithdrawComponent {
    void inject(AccountWithdrawActivity activity);
}
