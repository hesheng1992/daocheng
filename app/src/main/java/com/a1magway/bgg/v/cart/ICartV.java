package com.a1magway.bgg.v.cart;

import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.common.ILoadingV;

/**
 * Created by jph on 2017/8/14.
 */
public interface ICartV extends ILoadingV {

    void setRecyclerViewAdapter(RecyclerView.Adapter adapter);

    void stopRefresh();

    void stopLoadMore();

    void setLoadEnable();

    /**
     * 显示小计
     *
     * @param count
     * @param totalPrice
     */
    void showTotal(int count, double totalPrice);
}
