package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.AticleManagerModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.articleManager.ArticleManagerActivity;

import dagger.Component;


/**
 * Created by enid on 2018/8/22.
 */
@PerActivity
@Component(modules = AticleManagerModule.class,dependencies = AppComponent.class)
public interface AticleManagerComponent {
    void inject(ArticleManagerActivity activity);
}
