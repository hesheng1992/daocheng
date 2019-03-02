package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.EditAddressModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.personal.EditAddressActivity;

import dagger.Component;

/**
 * Created by lyx on 2017/8/16.
 */
@PerActivity
@Component(modules = EditAddressModule.class, dependencies = AppComponent.class)
public interface EditAddressComponent {
    void inject(EditAddressActivity editAddressActivity);
}
