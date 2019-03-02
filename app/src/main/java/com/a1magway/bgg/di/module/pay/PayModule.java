package com.a1magway.bgg.di.module.pay;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IPayData;
import com.a1magway.bgg.data.repository.NetPayData;
import com.a1magway.bgg.di.scope.PerFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jph on 2017/8/22.
 */
@Module
public class PayModule {

    private String mOrderNum;

    public PayModule(String orderNum) {
        mOrderNum = orderNum;
    }

    @PerFragment
    @Provides
    public IPayData provideIPayData(APIManager apiManager) {
        return new NetPayData(apiManager);
    }

    @Named(value = "OrderNum")
    @PerFragment
    @Provides
    public String provideOrderNum() {
        return mOrderNum;
    }
}
