package com.a1magway.bgg.di.component.member;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.member.MemberRegisterModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.member.MemberRegisterActivity;

import dagger.Component;

/**
 * author: Beaven
 * date: 2017/10/16 14:34
 */
@PerActivity
@Component(modules = MemberRegisterModule.class, dependencies = AppComponent.class)
public interface MemberRegisterComponent {

    void inject(MemberRegisterActivity activity);
}
