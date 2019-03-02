package com.a1magway.bgg.data.repository;

import android.util.Log;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.CollectionData;
import com.a1magway.bgg.data.net.APIManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/4.
 */

public class NetCollectionListData implements ICollectionListData{
    private APIManager mAPIManager;

    List<CollectionData> selectProducts = new ArrayList<>();

    public NetCollectionListData(APIManager APIManager) {
        mAPIManager = APIManager;
        Log.d("enid","NetCollectionListData execute");
    }

    @Override
    public Observable<String> addCollection(int pId, int userId) {
        return mAPIManager.addCollection(pId,userId);
    }

    @Override
    public Observable<APIResponse> cancelCollection(String id) {
        return mAPIManager.cancelCollection(id);
    }

    @Override
    public Observable<List<CollectionData>> getCollection(int userId) {
        return mAPIManager.getCollectionList(userId);
    }
}
