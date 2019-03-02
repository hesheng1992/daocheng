package com.a1magway.bgg.data.repository.mainhome;

import com.a1magway.bgg.data.entity.GoodsRecommendData;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/14.
 */

public interface ISpecialProductData {
    public Observable<GoodsRecommendData> getSubject(int user_id, int id, int order_by, int asc_desc, int page_id, int page_num);
}
