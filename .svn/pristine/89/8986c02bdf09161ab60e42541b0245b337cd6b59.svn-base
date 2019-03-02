package com.a1magway.bgg.common.adapter;

import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.common.adapter.attach.AttachRecyclerAdapter;

import java.util.List;

/**
 * Created by jph on 2017/8/24.
 */
public abstract class RxRecyclerAdapter<VH extends RecyclerView.ViewHolder>
        extends AttachRecyclerAdapter<VH> {

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        if (holder instanceof RxRecyclerVH) {
            //ViewHolder可能会被复用,而onViewAttachedToWindow又是在onBindViewHolder之后调用，
            // 所以在真正执行onBindViewHolder之前重置一次Rx
            ((RxRecyclerVH) holder).resetRxLife();
        }
        super.onBindViewHolder(holder, position, payloads);
    }
}
