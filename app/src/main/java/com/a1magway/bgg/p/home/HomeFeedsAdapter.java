package com.a1magway.bgg.p.home;

import android.view.ViewGroup;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.ActivityData;

import java.util.List;

/**
 * 首页专题列表的Adapter，不包含顶部banner和秒杀模块
 * Created by jph on 2017/7/24.
 */
public class HomeFeedsAdapter extends BaseRecyclerAdapter<HomeActivityVH, ActivityData> {
    public HomeFeedsAdapter(List<ActivityData> list) {
        super(list);
    }

    @Override
    public HomeActivityVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeActivityVH(parent);
    }

    @Override
    public void onRealBindViewHolder(HomeActivityVH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        holder.showViewContent(getItem(position));
    }
}
