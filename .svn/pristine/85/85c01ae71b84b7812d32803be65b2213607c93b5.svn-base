package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.IsCollectionData;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/19.
 */

public interface IProductInfoData {
    Observable<String> addCollection(int pId, int userId);

    Observable<APIResponse> cancelCollection(String id);

    Observable<IsCollectionData> isCollection(int pId, int userId);
}
