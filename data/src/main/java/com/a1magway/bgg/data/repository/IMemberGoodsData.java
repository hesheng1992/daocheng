package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.ProductItem;

import java.util.List;

import io.reactivex.Observable;

/**
 * author: Beaven
 * date: 2017/10/13 11:06
 */

public interface IMemberGoodsData {
    /**
     * 获取秒杀商品列表
     *
     * @param lastIdOfProduct 上一页数据最后一条数据id
     * @param pageSize        每页显示商品数 默认16
     */
    Observable<List<ProductItem>> getMemberGoods(int lastIdOfProduct, int pageSize);
}
