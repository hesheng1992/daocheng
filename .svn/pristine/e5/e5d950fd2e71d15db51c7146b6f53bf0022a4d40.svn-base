package com.a1magway.bgg.di.module;

import com.a1magway.bgg.v.login.UserBindPhoneContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by enid on 2018/8/3.
 */

@Module
public class UserBindPhoneModel {
    private UserBindPhoneContract.View view;
    public UserBindPhoneModel(UserBindPhoneContract.View view){
        this.view = view;
    }

    @Provides
    public UserBindPhoneContract.View provideSendVerificationCodeV() {
        return view;
    }


}
