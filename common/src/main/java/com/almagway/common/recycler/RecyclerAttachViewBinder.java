package com.almagway.common.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import me.drakeet.multitype.ItemViewBinder;

/**
 * 处理ViewHolder的attach事件，使用的ViewHolder必须是{@link RecyclerAttachVH}
 * Created by jph on 2017/7/27.
 */
public abstract class RecyclerAttachViewBinder<T, VH extends RecyclerView.ViewHolder>
        extends ItemViewBinder<T, VH> {

    @Override
    protected void onViewAttachedToWindow(@NonNull VH holder) {
        super.onViewAttachedToWindow(holder);
        if (holder instanceof RecyclerAttachVH) {
            ((RecyclerAttachVH) holder).onViewAttachedToWindow();
        }
    }

    @Override
    protected void onViewDetachedFromWindow(@NonNull VH holder) {
        super.onViewDetachedFromWindow(holder);
        if (holder instanceof RecyclerAttachVH) {
            ((RecyclerAttachVH) holder).onViewDetachedFromWindow();
        }
    }
}
