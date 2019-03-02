package com.a1magway.bgg.p.logistics;

import android.view.ViewGroup;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.OrderCommodity;

import java.util.List;

public class LogisticsOrderInfoAdapter extends BaseRecyclerAdapter<LogisticsOrderInfoVH,OrderCommodity> {


    public LogisticsOrderInfoAdapter(List<OrderCommodity> list) {
        super(list);
    }

    @Override
    public LogisticsOrderInfoVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new LogisticsOrderInfoVH(parent);
    }


    @Override
    public void onRealBindViewHolder(LogisticsOrderInfoVH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        holder.showViewContent(getItem(position));
    }
}
