package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.CateItem;
import com.a1magway.bgg.data.net.APIManager;

import java.util.List;

import io.reactivex.Observable;

/**
 * 从网络获取分类相关数据
 * Created by jph on 2017/7/19.
 */
public class NetCateData implements ICateData {
    APIManager mAPIManager;

    public NetCateData(APIManager APIManager) {
        mAPIManager = APIManager;
    }

    @Override
    public Observable<List<CateItem>> getCates() {
        return mAPIManager.getCates();
    }
}
