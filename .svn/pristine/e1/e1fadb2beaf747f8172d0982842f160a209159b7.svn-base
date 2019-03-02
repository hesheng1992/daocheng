package com.a1magway.bgg.di.component.order;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.order.OrderDetailsModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.order.OrderDetailsActivity;

import dagger.Component;

/**
 * Created by jph on 2017/8/19.
 */
@PerActivity
@Component(modules = OrderDetailsModule.class, dependencies = AppComponent.class)
public interface OrderDetailsComponent {

    void inject(OrderDetailsActivity orderDetailsActivity);
}
