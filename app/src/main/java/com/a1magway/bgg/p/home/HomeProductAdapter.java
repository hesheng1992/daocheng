package com.a1magway.bgg.p.home;

import android.view.ViewGroup;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.ActivityProduct;

import java.util.List;

/**
 * 首页专题对应的商品列表适配器
 * Created by jph on 2017/7/25.
 */
public class HomeProductAdapter extends BaseRecyclerAdapter<HomeProductVH, ActivityProduct> {

    public HomeProductAdapter(List<ActivityProduct> list) {
        super(list);
    }

    @Override
    public HomeProductVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeProductVH(parent);
    }

    @Override
    public void onRealBindViewHolder(HomeProductVH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        holder.showViewContent(getItem(position), this);
    }
}
