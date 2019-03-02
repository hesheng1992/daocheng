package com.a1magway.bgg.di.module.order;

import com.a1magway.bgg.data.entity.LogisticsDeliverData;
import com.a1magway.bgg.data.entity.OrderCommodity;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.ILogisticsData;
import com.a1magway.bgg.data.repository.NetLogisticsData;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.p.logistics.LogisticsAdapter;
import com.a1magway.bgg.p.logistics.LogisticsOrderInfoAdapter;
import com.a1magway.bgg.v.order.ILogisticsV;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by enid on 2018/6/23.
 */
@Module
public class LogisticsModule {

    private ILogisticsV mLogisticsV;

    private String mOuttradeno;

    public LogisticsModule(ILogisticsV logisticsV, String outtradeno) {
        this.mLogisticsV = logisticsV;
        this.mOuttradeno = outtradeno;
    }

    @PerActivity
    @Provides
    public ILogisticsV provideLogisticsV() {
        return mLogisticsV;
    }

    @PerActivity
    @Provides
    public ILogisticsData provideLogisticsData(APIManager apiManager) {
        return new NetLogisticsData(apiManager);
    }

    @PerActivity
    @Provides
    public String provideOuttradeno() {
        return mOuttradeno;
    }

    @Provides
    public LogisticsAdapter provideLogisticsAdapter(){
        return new LogisticsAdapter(new ArrayList<LogisticsDeliverData>());
    }

    @Provides
    public LogisticsOrderInfoAdapter provideLogisticsOrderInfoAdapter(){
        return new LogisticsOrderInfoAdapter(new ArrayList<OrderCommodity>());
    }

}
