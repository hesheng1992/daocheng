package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.LoginModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.login.LoginActivity;

import dagger.Component;

/**
 * Created by lyx on 2017/7/27.
 */
@PerActivity
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
