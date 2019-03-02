package com.a1magway.bgg.di.component.mainhome;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.mainhome.SpecialProductModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.mainhome.SpecialProductActivity;

import dagger.Component;

/**
 * Created by enid on 2018/6/14.
 */
@PerActivity
@Component(modules = SpecialProductModule.class,dependencies = AppComponent.class)
public interface SpecialProductComponent {
    void inject(SpecialProductActivity activity);
}
