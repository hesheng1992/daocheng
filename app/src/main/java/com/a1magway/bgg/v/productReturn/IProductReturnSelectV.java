package com.a1magway.bgg.v.productReturn;

import android.widget.Adapter;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.OrderCommodity;
import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.v.IView;

import java.util.List;

/**
 * Created by lm on 2018/8/30.
 */
public interface IProductReturnSelectV extends IView {
    public void GoodsInfo(BaseRecyclerAdapter mAdapter);
    public void selectNumber(Integer number);
    public void selectGoods(OrderItem orderItem);
}
