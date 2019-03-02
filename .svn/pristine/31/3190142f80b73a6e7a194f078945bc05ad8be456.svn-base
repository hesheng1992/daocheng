package com.a1magway.bgg.di.component.order;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.order.OrderPayModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.order.OrderPayActivity;

import dagger.Component;

/**
 * Created by enid on 2018/6/23.
 */
@PerActivity
@Component(modules = OrderPayModule.class,dependencies = AppComponent.class)
public interface OrderPayComponent {
    void inject(OrderPayActivity activity);
}
