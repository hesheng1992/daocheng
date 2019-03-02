package com.a1magway.bgg.di.module.product;

import com.a1magway.bgg.data.entity.Product;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IProductData;
import com.a1magway.bgg.data.repository.NetProductData;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.p.product.ProductDetailsP;
import com.a1magway.bgg.v.product.IProductDetailsV;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jph on 2017/8/8.
 */
@Module
public class ProDetailsModule {

    private IProductDetailsV mProductDetailsV;
    private int mProductId;

    public ProDetailsModule(IProductDetailsV productDetailsV, int productId) {
        mProductDetailsV = productDetailsV;
        mProductId = productId;
    }

    @PerActivity
    @Provides
    public IProductDetailsV provideProductDetailsV() {
        return mProductDetailsV;
    }

    @PerActivity
    @Provides
    public IProductData provideProductData(APIManager apiManager) {
        return new NetProductData(apiManager);
    }

    @Named(value = "productId")
    @Provides
    public int provideProductId() {
        return mProductId;
    }


    @Provides
    public Product provideProduct(ProductDetailsP productDetailsP) {
        return productDetailsP.getProduct();
    }
}
