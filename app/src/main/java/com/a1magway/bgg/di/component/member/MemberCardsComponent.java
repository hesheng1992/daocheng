package com.a1magway.bgg.di.component.member;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.member.MemberCardsModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.member.MemberCardActivity;

import dagger.Component;

/**
 * author: Beaven
 * date: 2017/10/20 11:30
 */
@PerActivity
@Component(modules = MemberCardsModule.class, dependencies = AppComponent.class)
public interface MemberCardsComponent {

    void inject(MemberCardActivity activity);
}
