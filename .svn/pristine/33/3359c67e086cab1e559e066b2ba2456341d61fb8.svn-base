package com.a1magway.bgg.p.logistics;

import android.view.ViewGroup;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.LogisticsDeliverData;

import java.util.List;

/**
 * Created by enid on 2018/6/15.
 */

public class LogisticsAdapter extends BaseRecyclerAdapter<LogisticsVH,LogisticsDeliverData>{
    public LogisticsAdapter(List<LogisticsDeliverData> list) {
        super(list);
    }

    @Override
    public LogisticsVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new LogisticsVH(parent);
    }

    @Override
    public void onRealBindViewHolder(LogisticsVH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        holder.showViewContent(getItem(position),position);
    }
}
