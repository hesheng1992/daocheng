package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.PersonalModule;
import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.v.personal.PersonalNewFragment;

import dagger.Component;

/**
 * Created by lyx on 2017/8/1.
 */
@PerFragment
@Component(modules = PersonalModule.class, dependencies = AppComponent.class)
public interface PersonalComponent {

    void inject(PersonalNewFragment personalNewFragment);
}
