package com.a1magway.bgg.di.component.saleorder;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.saleorder.SaleOrderListModule;
import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.v.saleorder.SaleOrderListFragment;

import dagger.Component;

/**
 * Created by enid on 2018/06/20.
 */
@PerFragment
@Component(modules = SaleOrderListModule.class, dependencies = AppComponent.class)
public interface SaleOrderListComponent {

    void inject(SaleOrderListFragment fragment);
}
