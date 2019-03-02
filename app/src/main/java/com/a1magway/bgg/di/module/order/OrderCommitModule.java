package com.a1magway.bgg.di.module.order;

import android.content.Intent;

import com.a1magway.bgg.data.entity.AgainRequestCatDataTag;
import com.a1magway.bgg.data.entity.OrderDetailsCommodity;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IOrderCommitData;
import com.a1magway.bgg.data.repository.NetOrderCommitData;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.p.order.ProListAdapter;
import com.a1magway.bgg.v.order.IOrderCommitV;
import com.a1magway.bgg.v.order.OrderCommitActivity;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jph on 2017/8/23.
 */
@Module
public class OrderCommitModule {

    private IOrderCommitV mOrderCommitV;
    private Intent mIntent;

    public OrderCommitModule(IOrderCommitV orderCommitV, Intent intent) {
        mOrderCommitV = orderCommitV;
        mIntent = intent;
    }

    @PerActivity
    @Provides
    public IOrderCommitV provideOrderCommitV() {
        return mOrderCommitV;
    }

    @Named(value = "CommodityList")
    @PerActivity
    @Provides
    public List<OrderDetailsCommodity> provideCommodityList() {
        return (List<OrderDetailsCommodity>) mIntent.getSerializableExtra(
                OrderCommitActivity.EXTRA_COMMODITY_LIST);
    }

    @Named(value = "IsSeckill")
    @PerActivity
    @Provides
    public boolean provideIsSeckill() {
        return mIntent.getBooleanExtra(OrderCommitActivity.EXTRA_IS_SECKILL, false);
    }

    @PerActivity
    @Provides
    public ProListAdapter provideProListAdapter(@Named(value = "CommodityList")
                                                        List<OrderDetailsCommodity> commodityList) {
        return new ProListAdapter(commodityList);
    }

//    @PerActivity
//    @Provides
//    public AddressData provideDefaultAddress() {
//        LoginData loginData = GlobalData.getInstance().getLoginData();
//        return loginData.getDefaultAddress();
//    }

    @PerActivity
    @Provides
    public IOrderCommitData provideOrderCommitData(APIManager apiManager) {
        return new NetOrderCommitData(apiManager);
    }

    @PerActivity
    @Provides
    public AgainRequestCatDataTag provideOrderRefreshTag(){
        if (mIntent.getSerializableExtra(OrderCommitActivity.EXTRA_REFRESH_TAG) == null){
            return new AgainRequestCatDataTag();
        }else {
            return (AgainRequestCatDataTag) mIntent.getSerializableExtra(OrderCommitActivity.EXTRA_REFRESH_TAG);
        }
    }

    @Named(value = "IsPingtuan")
    @PerActivity
    @Provides
    public boolean provideIsPingtuan() {
        return mIntent.getBooleanExtra(OrderCommitActivity.EXTRA_IS_PING_TUAN, false);
    }

    @Named(value = "IsMyCreatePt")
    @PerActivity
    @Provides
    public boolean provideIsMyCreatePingtuan() {
        return mIntent.getBooleanExtra(OrderCommitActivity.EXTRA_MY_CREATE_PT, false);
    }

    @Named(value = "CollageOrderId")
    @PerActivity
    @Provides
    public int provideGetCollageOrderId(){
        return mIntent.getIntExtra(OrderCommitActivity.EXTRA_COLLAGE_ORDER_ID,-100);
    }

}
