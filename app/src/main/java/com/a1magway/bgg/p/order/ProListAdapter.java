package com.a1magway.bgg.p.order;

import android.view.ViewGroup;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.OrderDetailsCommodity;

import java.util.List;

/**
 * 订单详情中商品列表的适配器
 * Created by jph on 2017/8/19.
 */
public class ProListAdapter extends BaseRecyclerAdapter<DetailsProVH, OrderDetailsCommodity> {
    public ProListAdapter(List<OrderDetailsCommodity> list) {
        super(list);
    }

    @Override
    public DetailsProVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new DetailsProVH(parent);
    }

    @Override
    public void onRealBindViewHolder(DetailsProVH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        if (getList().size()>1){
            holder.setShowText(true);
        }else{
            holder.setShowText(false);
        }

        holder.showViewContent(getItem(position));
    }
}
