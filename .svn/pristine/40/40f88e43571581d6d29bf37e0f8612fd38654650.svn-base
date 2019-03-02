package com.almagway.common.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * author: Beaven
 * date: 2017/10/25 20:44
 */

public abstract class RecyclerRxLifeViewBinder<T, VH extends RecyclerView.ViewHolder> extends
    RecyclerAttachViewBinder<T, VH> {

    @Override
    protected void onBindViewHolder(@NonNull VH holder, @NonNull T item, @NonNull List<Object> payloads) {
        if (holder instanceof RecyclerRxLifeVH) {
            //ViewHolder可能会被复用,而onViewAttachedToWindow又是在onBindViewHolder之后调用，
            // 所以在真正执行onBindViewHolder之前重置一次Rx
            ((RecyclerRxLifeVH) holder).resetRxLife();
        }
        super.onBindViewHolder(holder, item, payloads);
    }
}
