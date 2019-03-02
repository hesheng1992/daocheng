package com.a1magway.bgg.di.module.order;

import com.a1magway.bgg.data.entity.OrderDetailsCommodity;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IOrderDetailsData;
import com.a1magway.bgg.data.repository.NetOrderDetailsData;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.order.IOrderDetailsV;
import com.a1magway.bgg.p.order.ProListAdapter;

import java.util.ArrayList;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jph on 2017/8/19.
 */
@Module
public class OrderDetailsModule {

    private IOrderDetailsV mOrderDetailsV;
    private String mOrderNum;

    public OrderDetailsModule(IOrderDetailsV orderDetailsV, String orderNum) {
        mOrderDetailsV = orderDetailsV;
        mOrderNum = orderNum;
    }

    @PerActivity
    @Provides
    public IOrderDetailsV provideOrderDetailsV() {
        return mOrderDetailsV;
    }

    @PerActivity
    @Named(value = "OrderNum")
    @Provides
    public String provideOrderNum() {
        return mOrderNum;
    }

    @Provides
    public ProListAdapter provideProListAdapter() {
        return new ProListAdapter(new ArrayList<OrderDetailsCommodity>());
    }

    @PerActivity
    @Provides
    public IOrderDetailsData provideOrderDetailsData(APIManager apiManager) {
        return new NetOrderDetailsData(apiManager);
    }

}
