package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.AddressModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.personal.AddressManagerActivity;
import dagger.Component;

/**
 * Created by lyx on 2017/8/14.
 */
@PerActivity
@Component(modules = AddressModule.class, dependencies = AppComponent.class)
public interface AddressComponent {
    void inject(AddressManagerActivity managerActivity);
}
