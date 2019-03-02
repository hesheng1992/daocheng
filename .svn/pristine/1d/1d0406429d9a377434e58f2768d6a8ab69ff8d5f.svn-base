package com.a1magway.bgg.di.module.saleorder;

import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.ISaleOrderListData;
import com.a1magway.bgg.data.repository.NetSaleOrderListData;
import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.p.order.OrderListAdapter;
import com.a1magway.bgg.v.saleorder.ISaleOrderListV;

import java.util.ArrayList;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class SaleOrderListModule {

    private ISaleOrderListV mOrderListV;
    private int mOrderType;

    public SaleOrderListModule(ISaleOrderListV orderListV, int orderType) {
        mOrderListV = orderListV;
        mOrderType = orderType;
    }

    @PerFragment
    @Provides
    public ISaleOrderListV provideOrderListV() {
        return mOrderListV;
    }

    @PerFragment
    @Provides
    public ISaleOrderListData providSaleOrderListData(APIManager apiManager) {
        return new NetSaleOrderListData(apiManager);
    }

    @PerFragment
    @Provides
    public OrderListAdapter provideOrderListAdapter() {
        return new OrderListAdapter(new ArrayList<OrderItem>(),true);
    }

    @PerFragment
    @Named(value = "OrderType")
    @Provides
    public int provideOrderType() {
        return mOrderType;
    }
}
