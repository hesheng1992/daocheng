package com.a1magway.bgg.di.module.product;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IProductInfoData;
import com.a1magway.bgg.data.repository.NetProductInfoData;
import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.v.product.IProDetailsInfoV;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jph on 2017/8/8.
 */
@Module
public class ProDetailsInfoModule {

    private IProDetailsInfoV mProDetailsInfoV;

    public ProDetailsInfoModule(IProDetailsInfoV proDetailsInfoV) {
        mProDetailsInfoV = proDetailsInfoV;
    }

    @PerFragment
    @Provides
    public IProDetailsInfoV provideProDetailsInfoV() {
        return mProDetailsInfoV;
    }

    @Provides
    public IProductInfoData provideProductInfoData(APIManager apiManager) {
        return new NetProductInfoData(apiManager);
    }

}
