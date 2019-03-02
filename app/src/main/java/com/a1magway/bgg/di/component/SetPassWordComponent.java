package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.SetPasswordModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.login.PasswordResetActivity;

import dagger.Component;

/**
 * Created by lyx on 2017/8/7.
 */
@PerActivity
@Component(modules = SetPasswordModule.class, dependencies = AppComponent.class)
public interface SetPassWordComponent {
 void inject(PasswordResetActivity passwordResetActivity);
}
