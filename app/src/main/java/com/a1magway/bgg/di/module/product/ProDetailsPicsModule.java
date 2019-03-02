package com.a1magway.bgg.di.module.product;

import com.a1magway.bgg.v.product.IDetailPicsV;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jph on 2017/8/9.
 */
@Module
public class ProDetailsPicsModule {

    private IDetailPicsV mDetailPicsV;

    public ProDetailsPicsModule(IDetailPicsV detailPicsV) {
        mDetailPicsV = detailPicsV;
    }

    @Provides
    public IDetailPicsV provideDetailPicsV() {
        return mDetailPicsV;
    }
}
