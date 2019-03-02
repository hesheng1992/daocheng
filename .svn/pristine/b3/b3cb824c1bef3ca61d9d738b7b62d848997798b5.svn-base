package com.a1magway.bgg.di.component.account;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.account.AddAccountModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.account.AddAccountActivity;

import dagger.Component;

/**
 * Created by enid on 2018/6/7.
 */
@PerActivity
@Component(modules = AddAccountModule.class,dependencies = AppComponent.class)
public interface AddAccountComponent {
    void inject(AddAccountActivity activity);
}
