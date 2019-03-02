package com.a1magway.bgg.p.search;

import android.view.ViewGroup;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.FilterCate1;

import java.util.List;

/**
 * 筛选一级分类列表适配器
 * Created by jph on 2017/8/3.
 */
public class FilterCate1Adapter extends BaseRecyclerAdapter<FilterCate1VH, FilterCate1> {
    private int mValidCateId = -1;//有效的分类id，若该值大于0则不匹配的item设置无效

    public FilterCate1Adapter(List<FilterCate1> list) {
        super(list);
    }

    @Override
    public FilterCate1VH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new FilterCate1VH(parent);
    }

    @Override
    public void onRealBindViewHolder(FilterCate1VH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        holder.showViewContent(getItem(position));
        boolean enable = mValidCateId <= 0 || getItem(position).getId() == mValidCateId;
        holder.setEnable(enable);
        if (!enable) {
            //不允许item事件
            holder.itemView.setOnClickListener(null);
        }
    }

    public void setValidCateId(int validCateId) {
        mValidCateId = validCateId;
    }
}
