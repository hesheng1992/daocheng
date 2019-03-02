package com.a1magway.bgg.v.personal;

import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.common.ILoadingV;

/**
 * Created by lyx on 2017/8/12.
 */
public interface IAddressV extends ILoadingV {

    void setRecyclerViewAdapter(RecyclerView.Adapter adapter);

    void stopRefresh();

    void setLoadable(boolean loadable);

    void stopLoadMore();

    boolean needAddressData();
}
