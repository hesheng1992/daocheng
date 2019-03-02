package com.a1magway.bgg.data.repository.mainhome;

import com.a1magway.bgg.data.entity.GoodsRecommendData;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/14.
 */

public class NetSpecialProductData implements ISpecialProductData {

    APIManager apiManager;

    public NetSpecialProductData(APIManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public Observable<GoodsRecommendData> getSubject(int user_id, int id, int order_by, int asc_desc,int page_id, int page_num) {
        return apiManager.getSubject(user_id,id,order_by,asc_desc,page_id,page_num);
    }
}
