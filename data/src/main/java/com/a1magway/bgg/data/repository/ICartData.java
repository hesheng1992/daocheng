package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.CartProduct;

import java.util.List;

import io.reactivex.Observable;

/**
 * 购物车对应的数据接口
 * Created by jph on 2017/8/14.
 */
public interface ICartData {

    Observable<List<CartProduct>> getCart(int userId, int lastCartProId);

    /**
     * 改变商品数量
     *
     * @param userId
     * @param type 0增加，1减少
     * @param shopCartId
     * @return
     */
    Observable<Void> changeProCount(int userId, int type, int shopCartId);

    /**
     * 删除购物车商品
     *
     * @param userId
     * @param shopCartId
     * @return
     */
    Observable<Void> deleteProduct(int userId, int shopCartId);
}
