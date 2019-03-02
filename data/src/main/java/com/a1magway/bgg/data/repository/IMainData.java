package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.CartNum;

import io.reactivex.Observable;

/**
 * Created by jph on 2017/8/25.
 */
public interface IMainData {

    Observable<CartNum> getCartNum();
}
