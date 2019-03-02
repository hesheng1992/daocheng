package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.AppNavigationData;
import com.a1magway.bgg.data.entity.LoginData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/13.
 */

public interface IMainHomeData {
    Observable<List<AppNavigationData>> getAppNavigation() ;
    Observable<LoginData> thirdPartLogin(String open_id);
}
