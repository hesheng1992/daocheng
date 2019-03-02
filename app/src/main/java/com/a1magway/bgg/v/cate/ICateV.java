package com.a1magway.bgg.v.cate;

import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.common.ILoadingV;

/**
 * 分类对应的V
 * Created by jph on 2017/7/20.
 */
public interface ICateV extends ILoadingV{

    void setRecyclerViewAdapter(RecyclerView.Adapter adapter);

    void stopRefresh();
}
