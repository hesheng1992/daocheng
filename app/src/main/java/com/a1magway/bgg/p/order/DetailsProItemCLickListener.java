package com.a1magway.bgg.p.order;

import android.view.View;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.common.adapter.IAdapterData;
import com.a1magway.bgg.data.entity.OrderDetailsCommodity;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.v.product.ProductDetailsActivity;

import static com.a1magway.bgg.App.getContext;

/**
 * 订单详情商品列表点击事件
 * Created by jph on 2017/8/23.
 */
public class DetailsProItemCLickListener implements BaseRecyclerAdapter.OnItemClickListener {
    private IAdapterData<OrderDetailsCommodity> mAdapter;


    public DetailsProItemCLickListener(IAdapterData<OrderDetailsCommodity> adapter) {
        mAdapter = adapter;
    }


    @Override
    public void onItemClick(View v, int position) {
        OrderDetailsCommodity commodity = mAdapter.getItem(position);

        if (!commodity.isValid()) {
            Toaster.showShort(getContext(), commodity.getValidInfo());
            return;
        }

        ProductDetailsActivity.start(getContext(), commodity.getProductId(),commodity.getSubject_id());
    }


    @Override
    public void onItemLongClick(View v, int position) {

    }
}
