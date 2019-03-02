package com.a1magway.bgg.di.module.order;

import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IOrderListData;
import com.a1magway.bgg.data.repository.NetOrderListData;
import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.p.order.OrderListAdapter;
import com.a1magway.bgg.v.order.IOrderListV;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;
import javax.inject.Named;

/** Created by jph on 2017/8/17. */
@Module
public class OrderListModule {

    private IOrderListV mOrderListV;
    private int mOrderType;

    public OrderListModule(IOrderListV orderListV, int orderType) {
        mOrderListV = orderListV;
        mOrderType = orderType;
    }

    @PerFragment
    @Provides
    public IOrderListV provideOrderListV() {
        return mOrderListV;
    }

    @PerFragment
    @Provides
    public IOrderListData provideOrderListData(APIManager apiManager) {
        return new NetOrderListData(apiManager);
    }

    @PerFragment
    @Provides
    public OrderListAdapter provideOrderListAdapter() {
        return new OrderListAdapter(new ArrayList<OrderItem>(),false);
    }

    @PerFragment
    @Named(value = "OrderType")
    @Provides
    public int provideOrderType() {
        return mOrderType;
    }
}
