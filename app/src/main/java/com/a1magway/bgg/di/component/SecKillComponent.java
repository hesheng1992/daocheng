package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.SecKillModule;
import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.v.seckill.SecKillFragment;

import dagger.Component;

/**
 * Created by jph on 2017/7/28.
 */
@PerFragment
@Component(modules = SecKillModule.class, dependencies = AppComponent.class)
public interface SecKillComponent {

    void inject(SecKillFragment secKillFragment);
}
