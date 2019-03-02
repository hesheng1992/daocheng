package com.a1magway.bgg.common.adapter.attach;

import android.support.v7.widget.RecyclerView;

import com.xxxrecylcerview.XXXAdapter;

/**
 * 处理ViewHolder的attach事件，使用的ViewHolder必须是{@link AttachRecyclerVH}
 * Created by jph on 2017/7/27.
 */
public abstract class AttachRecyclerAdapter<VH extends RecyclerView.ViewHolder>
        extends XXXAdapter<VH> {

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder instanceof AttachRecyclerVH) {
            ((AttachRecyclerVH) holder).onViewAttachedToWindow();
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (holder instanceof AttachRecyclerVH) {
            ((AttachRecyclerVH) holder).onViewDetachedFromWindow();
        }
    }
}
