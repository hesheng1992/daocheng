package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.ProductItem;
import com.a1magway.bgg.data.net.APIManager;

import java.util.List;

import io.reactivex.Observable;

/**
 * author: Beaven
 * date: 2017/10/13 11:07
 */

public class NetMemberGoodsData implements IMemberGoodsData {

    private APIManager apiManager;

    public NetMemberGoodsData(APIManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public Observable<List<ProductItem>> getMemberGoods(int lastIdOfProduct, int pageSize) {
        return apiManager.getMemberGoods(lastIdOfProduct, pageSize);
    }
}
