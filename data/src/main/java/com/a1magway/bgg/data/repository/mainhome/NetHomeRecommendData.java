package com.a1magway.bgg.data.repository.mainhome;

import com.a1magway.bgg.data.entity.GoodsRecommendData;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/14.
 */

public class NetHomeRecommendData implements IHomeRecommendData {

    APIManager apiManager;

    public NetHomeRecommendData(APIManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public Observable<GoodsRecommendData> getGoodGoodsRecommendsList(int user_id,int type, int order_by, int asc_descint, int current_page,int page_num) {
        return apiManager.getGoodGoodsRecommendsList(user_id,type,order_by,asc_descint,current_page,page_num);
    }
}
