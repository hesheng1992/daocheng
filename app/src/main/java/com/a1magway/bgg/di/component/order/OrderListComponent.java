package com.a1magway.bgg.di.component.order;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.order.OrderListModule;
import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.v.order.OrderListFragment;

import dagger.Component;

/**
 * Created by jph on 2017/8/17.
 */
@PerFragment
@Component(modules = OrderListModule.class, dependencies = AppComponent.class)
public interface OrderListComponent {

    void inject(OrderListFragment orderListFragment);
}
