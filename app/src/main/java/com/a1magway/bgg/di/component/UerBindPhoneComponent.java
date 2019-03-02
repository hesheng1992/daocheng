package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.UserBindPhoneModel;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.login.UserBindPhoneActivity;

import dagger.Component;

/**
 * Created by enid on 2018/8/3.
 */

@PerActivity
@Component(modules = UserBindPhoneModel.class, dependencies = AppComponent.class)
public interface UerBindPhoneComponent {
    void inject(UserBindPhoneActivity userBindPhoneActivity);
}
