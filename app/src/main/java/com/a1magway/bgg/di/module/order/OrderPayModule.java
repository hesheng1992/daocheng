package com.a1magway.bgg.di.module.order;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IOrderDetailsData;
import com.a1magway.bgg.data.repository.IPayData;
import com.a1magway.bgg.data.repository.NetOrderDetailsData;
import com.a1magway.bgg.data.repository.NetPayData;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.order.IOrderPayV;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by enid on 2018/6/23.
 */
@Module
public class OrderPayModule {

    private IOrderPayV mOrderPayV;

    private String mOrderNum;

    public OrderPayModule(IOrderPayV orderPayV,String orderNum) {
        this.mOrderPayV = orderPayV;
        this.mOrderNum = orderNum;
    }

    @PerActivity
    @Provides
    public IOrderPayV provideOrderPayV(){
        return mOrderPayV;
    }

    @PerActivity
    @Provides
    public IPayData provideIPayData(APIManager apiManager) {
        return new NetPayData(apiManager);
    }

    @PerActivity
    @Provides
    public IOrderDetailsData provideOrderDetailsData(APIManager apiManager) {
        return new NetOrderDetailsData(apiManager);
    }

    @Named(value = "OrderNum")
    @PerActivity
    @Provides
    public String provideOrderNum() {
        return mOrderNum;
    }

}
