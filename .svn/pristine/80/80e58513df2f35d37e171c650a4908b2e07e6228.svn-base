package com.a1magway.bgg.di.component.product;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.product.ProDetailsInfoModule;
import com.a1magway.bgg.di.module.product.ProDetailsModule;
import com.a1magway.bgg.di.module.product.ProDetailsPicsModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.product.ProductDetailsActivity;

import dagger.Component;

/**
 * Created by jph on 2017/8/8.
 */
@PerActivity
@Component(modules = ProDetailsModule.class, dependencies = AppComponent.class)
public interface ProDetailsComponent {

    void inject(ProductDetailsActivity productDetailsActivity);

    ProDetailsInfoComponent getInfoComponent(ProDetailsInfoModule infoModule);

    ProDetailsPicsComponent getPicComponent(ProDetailsPicsModule picsModule);
}
