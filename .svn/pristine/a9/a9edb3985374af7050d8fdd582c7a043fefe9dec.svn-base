package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.CartModule;
import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.v.cart.CartFragment;

import dagger.Component;

/**
 * Created by jph on 2017/8/14.
 */
@PerFragment
@Component(modules = CartModule.class, dependencies = AppComponent.class)
public interface CartComponent {

    void inject(CartFragment cartFragment);
}
