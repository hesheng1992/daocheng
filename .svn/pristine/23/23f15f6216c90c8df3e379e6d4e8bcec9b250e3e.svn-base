package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.CartProduct;
import com.a1magway.bgg.data.net.APIManager;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by jph on 2017/8/14.
 */
public class NetCartData implements ICartData {

    private APIManager mAPIManager;

    public NetCartData(APIManager APIManager) {
        mAPIManager = APIManager;
    }

    @Override
    public Observable<List<CartProduct>> getCart(int userId, int lastCartProId) {
        return mAPIManager.getCart(userId, lastCartProId);
    }

    @Override
    public Observable<Void> changeProCount(int userId, int type, int shopCartId) {
        return mAPIManager.changeCartProCount(userId, type, shopCartId);
    }

    @Override
    public Observable<Void> deleteProduct(int userId, int shopCartId) {
        return mAPIManager.deleteCartPro(userId, shopCartId);
    }


}
