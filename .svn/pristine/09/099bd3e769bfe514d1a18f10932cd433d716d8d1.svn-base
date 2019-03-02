package com.a1magway.bgg.data.repository.mainhome;

import com.a1magway.bgg.data.entity.HomeSubjectBean;
import com.a1magway.bgg.data.net.APIManager;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/13.
 */

public class NetHomeSpecialData implements IHomeSpecialData {
    APIManager apiManager;

    public NetHomeSpecialData(APIManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public Observable<List<HomeSubjectBean>> getSubjectList(int type) {
        return apiManager.getSubjectList(type);
    }
}
