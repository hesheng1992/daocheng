package com.a1magway.bgg.data.entity;

import java.io.Serializable;

/**
 * 购物车小红点
 * Created by jph on 2017/8/25.
 */
public class CartNum implements Serializable {

    private static final long serialVersionUID = 3520650900038114501L;
    private int shopCartCount;

    public int getShopCartCount() {
        return shopCartCount;
    }

    public void setShopCartCount(int shopCartCount) {
        this.shopCartCount = shopCartCount;
    }
}
