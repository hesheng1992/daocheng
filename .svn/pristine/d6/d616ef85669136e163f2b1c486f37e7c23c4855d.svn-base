package com.a1magway.bgg.data.repository;

import android.support.annotation.Nullable;

import com.a1magway.bgg.data.entity.FilterCate1;
import com.a1magway.bgg.data.entity.ProductBean;
import com.a1magway.bgg.data.entity.ProductFilterRule;

import java.util.List;

import io.reactivex.Observable;

/**
 * 搜索数据交互
 * Created by jph on 2017/7/31.
 */
public interface ISearchData {

    /**
     * 搜索商品
     *
     * @param keyword
     * @param lastIdOfProduct
     * @param productFilterRule 筛选条件
     * @return
     */
    Observable<List<ProductBean>> searchProduct(String keyword, int lastIdOfProduct,
                                                @Nullable ProductFilterRule productFilterRule);

    /**
     * 得到搜索筛选的分类数据
     *
     * @return
     */
    Observable<List<FilterCate1>> getFilterCates();
}
