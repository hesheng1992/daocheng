package com.a1magway.bgg.di.component.member;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.member.MemberUpgradeInfoModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.member.MemberUpgradeInfoActivity;

import dagger.Component;

/**
 * author: Beaven
 * date: 2017/10/13 14:33
 */
@PerActivity
@Component(modules = MemberUpgradeInfoModule.class, dependencies = AppComponent.class)
public interface MemberUpgradeInfoComponent {

    void inject(MemberUpgradeInfoActivity activity);
}
