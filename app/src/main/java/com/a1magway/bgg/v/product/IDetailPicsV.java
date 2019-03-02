package com.a1magway.bgg.v.product;

import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.v.IView;

/**
 * Created by jph on 2017/8/9.
 */
public interface IDetailPicsV extends IView {

    void setRecyclerViewAdapter(RecyclerView.Adapter adapter);
}
