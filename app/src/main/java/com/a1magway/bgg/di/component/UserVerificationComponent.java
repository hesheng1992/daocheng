package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.UserVerificationModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.login.UserVerificationActivity;

import dagger.Component;

/**
 * Created by lyx on 2017/8/1.
 */
@PerActivity
@Component(modules = UserVerificationModule.class, dependencies = AppComponent.class)
public interface UserVerificationComponent {
    void inject(UserVerificationActivity userVerificationActivity);
}
