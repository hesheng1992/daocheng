package com.a1magway.bgg.di.component.order;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.order.OrderCommitModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.order.OrderCommitActivity;

import dagger.Component;

/**
 * Created by jph on 2017/8/23.
 */
@PerActivity
@Component(modules = OrderCommitModule.class, dependencies = AppComponent.class)
public interface OrderCommitComponent {

    void inject(OrderCommitActivity orderCommitActivity);
}
