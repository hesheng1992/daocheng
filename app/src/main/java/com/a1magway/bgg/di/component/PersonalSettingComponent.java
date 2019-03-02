package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.PersonalSettingModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.personal.PersonalSettingActivity;

import dagger.Component;

/**
 * Created by lyx on 2017/8/8.
 */
@PerActivity
@Component(modules = PersonalSettingModule.class, dependencies = AppComponent.class)
public interface PersonalSettingComponent {
    void inject(PersonalSettingActivity personalSettingActivity);
}
