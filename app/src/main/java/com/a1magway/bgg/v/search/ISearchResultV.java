package com.a1magway.bgg.v.search;

import com.a1magway.bgg.common.ILoadingV;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;

/**
 * Created by jph on 2017/7/31.
 */
public interface ISearchResultV extends ILoadingV {

    void setRecyclerViewAdapter(BaseRecyclerAdapter adapter);

    void stopRefresh();

    void stopLoadMore();

    void startRefresh();

    void setLoadable(boolean loadable);

}
