package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.BrandMuseumModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.cate.BrandMuseumActivity;

import dagger.Component;

/**
 * Created by jph on 2017/7/29.
 */
@PerActivity
@Component(modules = BrandMuseumModule.class, dependencies = AppComponent.class)
public interface BrandMuseumComponent {

    void inject(BrandMuseumActivity brandMuseumActivity);
}
