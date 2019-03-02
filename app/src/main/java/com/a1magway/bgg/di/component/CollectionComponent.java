package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.ProductCollectionModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.collection.CollectionActivity;

import dagger.Component;

/**
 * Created by jph on 2017/8/14.
 */
@PerActivity
@Component(modules = ProductCollectionModule.class, dependencies = AppComponent.class)
public interface CollectionComponent {

    void inject(CollectionActivity activity);
}
