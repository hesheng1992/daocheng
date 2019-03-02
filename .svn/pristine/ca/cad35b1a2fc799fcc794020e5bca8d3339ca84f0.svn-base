package com.a1magway.bgg.v.order;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.common.ILoadingV;

/**
 * Created by jph on 2017/8/17.
 */
public interface IOrderListV extends ILoadingV {

    void setRecyclerViewAdapter(RecyclerView.Adapter adapter);

    void stopRefresh();

    void stopLoadMore();

    void setLoadable(boolean loadable);

    /**
     * 显示支付弹窗
     *
     * @param orderNum
     */
    void showPayDialog(String orderNum);

    FragmentManager getFragmentMg();

}
