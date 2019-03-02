package com.a1magway.bgg.di.component.member;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.member.MemberGoodsModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.member.MemberGoodsActivity;

import dagger.Component;

/**
 * author: Beaven
 * date: 2017/10/13 10:52
 */
@PerActivity
@Component(modules = MemberGoodsModule.class, dependencies = AppComponent.class)
public interface MemberGoodsComponent {

    void inject(MemberGoodsActivity activity);
}
