package com.a1magway.bgg.data.repository;

import android.support.annotation.Nullable;

import com.a1magway.bgg.data.entity.FilterCate1;
import com.a1magway.bgg.data.entity.ProductBean;
import com.a1magway.bgg.data.entity.ProductFilterRule;
import com.a1magway.bgg.data.net.APIManager;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by jph on 2017/7/31.
 */
public class NetSearchData implements ISearchData {

    APIManager mAPIManager;

    public NetSearchData(APIManager APIManager) {
        mAPIManager = APIManager;
    }

    @Override
    public Observable<List<ProductBean>> searchProduct( String keyword, int lastIdOfProduct,
                                                       @Nullable ProductFilterRule productFilterRule) {
        return mAPIManager.searchProduct( keyword, lastIdOfProduct, productFilterRule);
    }

    @Override
    public Observable<List<FilterCate1>> getFilterCates() {
        return mAPIManager.getFilterCates();
    }
}
