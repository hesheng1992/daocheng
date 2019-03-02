package com.a1magway.bgg.p.product;

import android.view.ViewGroup;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;

import java.util.List;

/**
 * 商品详情-下方多图展示列表的适配器
 * Created by jph on 2017/8/9.
 */
public class DetailsPicsAdapter extends BaseRecyclerAdapter<DetailsPicVH, String> {
    public DetailsPicsAdapter(List<String> list) {
        super(list);
    }

    @Override
    public DetailsPicVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new DetailsPicVH(parent);
    }

    @Override
    public void onRealBindViewHolder(DetailsPicVH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        holder.showViewContent(getItem(position));
    }
}
