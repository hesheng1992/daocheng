package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.ModifyPwdModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.personal.ModifyPwdActivity;

import dagger.Component;

/**
 * Created by lyx on 2017/8/8.
 */
@PerActivity
@Component(modules = ModifyPwdModule.class, dependencies = AppComponent.class)
public interface ModifyPwdComponent {
    void inject(ModifyPwdActivity modifyPwdActivity);
}
