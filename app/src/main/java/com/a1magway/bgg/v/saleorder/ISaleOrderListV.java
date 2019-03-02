package com.a1magway.bgg.v.saleorder;

import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.common.ILoadingV;

/**
 * Created by jph on 2017/8/17.
 */
public interface ISaleOrderListV extends ILoadingV {

    void setRecyclerViewAdapter(RecyclerView.Adapter adapter);

    void stopRefresh();

    void stopLoadMore();

    void setLoadable(boolean loadable);

}
