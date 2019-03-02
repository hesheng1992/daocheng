package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.IsCollectionData;
import com.a1magway.bgg.data.entity.Product;

import io.reactivex.Observable;

/**
 * Created by jph on 2017/8/7.
 */
public interface IProductData {

    Observable<Product> getProductDetails(int productId);

    Observable<Product> getProductDetails(int productId,int subject_id);

    Observable<String> add2Cart(int userId, int productId, int skuId, int count);

    Observable<String> addCollection(int pId, int userId);

    Observable<APIResponse> cancelCollection(int id);

    Observable<IsCollectionData> isCollection(int pId, int userId);
}
