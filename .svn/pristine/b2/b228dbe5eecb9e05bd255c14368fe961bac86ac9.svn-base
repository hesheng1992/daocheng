package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.CateModule;
import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.v.cate.CateFragment;

import dagger.Component;

/**
 * 分类模块对应的Component
 * Created by jph on 2017/7/19.
 */
@PerFragment
@Component(modules = CateModule.class, dependencies = AppComponent.class)
public interface CateComponent {

    void inject(CateFragment cateFragment);
}
