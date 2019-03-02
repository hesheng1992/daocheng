package com.a1magway.bgg.di.module.mainhome;

import com.a1magway.bgg.data.entity.ProductBean;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.mainhome.ISpecialProductData;
import com.a1magway.bgg.data.repository.mainhome.NetSpecialProductData;
import com.a1magway.bgg.v.mainhome.SpecialProductContract;
import com.a1magway.bgg.v.mainhome.ProductGridAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by enid on 2018/6/14.
 */
@Module
public class SpecialProductModule {
    SpecialProductContract.View mSpecialProductV;

    public SpecialProductModule(SpecialProductContract.View mSpecialProductV) {
        this.mSpecialProductV = mSpecialProductV;
    }

    @Provides
    public SpecialProductContract.View provideSpecialProductV(){
        return this.mSpecialProductV;
    }

    @Provides
    public ISpecialProductData provideSpecialProductData(APIManager apiManager){
        return new NetSpecialProductData(apiManager);
    }

    @Provides
    public ProductGridAdapter provideAdapter(){
        return new ProductGridAdapter(new ArrayList<ProductBean>());
    }
}
