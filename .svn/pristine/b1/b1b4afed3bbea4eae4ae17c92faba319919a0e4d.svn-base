package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.IsCollectionData;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by jph on 2017/8/8.
 */
public class NetProductInfoData implements IProductInfoData {

    private APIManager mAPIManager;

    public NetProductInfoData(APIManager APIManager) {
        mAPIManager = APIManager;
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
    public Observable<IsCollectionData> isCollection(int pId, int userId) {
        return mAPIManager.isCollection(pId,userId);
    }
}
