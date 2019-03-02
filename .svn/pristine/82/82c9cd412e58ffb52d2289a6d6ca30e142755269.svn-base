package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.CollectionData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/4.
 */

public interface ICollectionListData {
    Observable<String> addCollection(int pId, int userId);

    Observable<APIResponse> cancelCollection(String id);

    Observable<List<CollectionData>> getCollection(int userId);

}
