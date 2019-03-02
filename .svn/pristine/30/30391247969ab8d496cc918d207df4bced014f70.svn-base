package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.LogisticsInfoData;
import com.a1magway.bgg.data.net.APIManager;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/25.
 */

public class NetLogisticsData implements ILogisticsData {

    private APIManager mApiManager;

    public NetLogisticsData(APIManager mApiManager) {
        this.mApiManager = mApiManager;
    }

    @Override
    public Observable<List<LogisticsInfoData>> getLogistics(String outtradeno) {
        return mApiManager.getLogistics(outtradeno);
    }
}
