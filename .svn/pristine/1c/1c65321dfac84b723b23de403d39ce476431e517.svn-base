package com.a1magway.bgg.di.component.order;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.order.LogisticsModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.order.LogisticsActivity;

import dagger.Component;

/**
 * Created by enid on 2018/6/23.
 */
@PerActivity
@Component(modules = LogisticsModule.class,dependencies = AppComponent.class)
public interface LogisticsComponent {
    void inject(LogisticsActivity activity);
}
