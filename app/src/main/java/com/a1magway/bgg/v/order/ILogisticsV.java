package com.a1magway.bgg.v.order;

import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.common.ILoadingV;

/**
 * Created by enid on 2018/6/25.
 */

public interface ILogisticsV extends ILoadingV {
    void setRecyclerViewAdapter(RecyclerView.Adapter adapter);

    void setDeliverCompany(String deliverCompany);

    void setDeliverNum(String delivernum);

    void setOrderInfoAdapter(RecyclerView.Adapter adapter);

    void setOrderNum(Integer num);
}
