package com.a1magway.bgg.p.order;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.a1magway.bgg.data.entity.LogisticsData;
import com.a1magway.bgg.data.entity.LogisticsDeliverData;
import com.a1magway.bgg.data.entity.LogisticsInfoData;
import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.data.repository.ILogisticsData;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.p.logistics.LogisticsAdapter;
import com.a1magway.bgg.p.logistics.LogisticsOrderInfoAdapter;
import com.a1magway.bgg.v.order.ILogisticsV;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/23.
 */

public class LogisticsP extends BaseLoadP<List<LogisticsInfoData>, ILogisticsV> {

    private static final String TAG = LogisticsP.class.getSimpleName();

    @Inject
    ILogisticsData mLogisticsData;

    @Inject
    String mOouttradeno;

    @Inject
    LogisticsAdapter mLogisticsAdapter;

    @Inject
    LogisticsOrderInfoAdapter mLogisticsOrderInfoAdapter;

    @Inject
    public LogisticsP(@NonNull ILogisticsV view) {
        super(view);
    }

    @Nullable
    @Override
    public Observable<List<LogisticsInfoData>> getDataObservable() {
        return mLogisticsData.getLogistics(mOouttradeno);
    }

    @Override
    protected void onLoadSuccess(List<LogisticsInfoData> data) {
        super.onLoadSuccess(data);
        if (data != null) {
            LogisticsData logistics = data.get(0).getLogistics();
            if (logistics != null) {
                if (logistics.getData()==null|logistics.getData().size()==0){
                    LogisticsDeliverData logisticsDeliverData=new LogisticsDeliverData("",logistics.getMessage());
                    logistics.getData().add(logisticsDeliverData);
                }
                mLogisticsAdapter.setList(logistics.getData());
                mView.setRecyclerViewAdapter(mLogisticsAdapter);
                mView.setDeliverCompany(logistics.getCom());
                mView.setDeliverNum(logistics.getNu());
            }
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
//        mView.setRecyclerViewAdapter(mLogisticsAdapter);
        loadData(true);
    }

    public void setOrderInfo(OrderItem orderItem){
        mLogisticsOrderInfoAdapter.setList(orderItem.getSkuList());
        mView.setOrderInfoAdapter(mLogisticsOrderInfoAdapter);
        mView.setOrderNum(orderItem.getCount());
    }

}
