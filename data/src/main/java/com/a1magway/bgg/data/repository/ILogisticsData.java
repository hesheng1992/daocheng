package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.LogisticsInfoData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/25.
 */

public interface ILogisticsData {
    public Observable<List<LogisticsInfoData>> getLogistics (String outtradeno);
}
