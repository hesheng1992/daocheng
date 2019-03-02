package com.a1magway.bgg.v.mainhome;

import android.view.ViewGroup;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.HomeSubjectBean;

import java.util.List;

/**
 * Created by enid on 2018/6/11.
 */

public class SpecialDivisionAdapter extends BaseRecyclerAdapter<SpecialDivisionVH,HomeSubjectBean>{


    public SpecialDivisionAdapter(List<HomeSubjectBean> list) {
        super(list);
    }

    @Override
    public SpecialDivisionVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new SpecialDivisionVH(parent);
    }

    @Override
    public void onRealBindViewHolder(SpecialDivisionVH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        holder.showViewContent(getItem(position));
    }
}
