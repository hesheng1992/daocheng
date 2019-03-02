package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.IsCollectionData;
import com.a1magway.bgg.data.entity.Product;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by jph on 2017/8/8.
 */
public class NetProductData implements IProductData {

    private APIManager mAPIManager;

    public NetProductData(APIManager APIManager) {
        mAPIManager = APIManager;
    }

    @Override
    public Observable<Product> getProductDetails(int productId) {
        return mAPIManager.getProductDetails(productId);
    }

    @Override
    public Observable<Product> getProductDetails(int productId,int subject_id) {
        return mAPIManager.getProductDetails(productId,subject_id);
    }

    @Override
    public Observable<String> add2Cart(int userId, int productId, int skuId, int count) {
        return mAPIManager.add2Cart(userId, productId, skuId, count);
    }

    @Override
    public Observable<String> addCollection(int pId, int userId) {
        return mAPIManager.addCollection(pId,userId);
    }

    @Override
    public Observable<APIResponse> cancelCollection(int id) {
        return null;
    }

    @Override
    public Observable<IsCollectionData> isCollection(int pId, int userId) {
        return mAPIManager.isCollection(pId,userId);
    }
}
