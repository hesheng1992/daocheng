package com.a1magway.bgg.common.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.almagway.common.recycler.RecyclerRxLifeVH;

import butterknife.ButterKnife;

/**
 * 绑定数据的RecyclerView列表对应的ViewHolder基类
 * Created by jph on 2016/7/19.
 */
public abstract class BaseRecyclerVH<T> extends RecyclerRxLifeVH {
    public BaseRecyclerVH(@NonNull ViewGroup parent, int layoutResId) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false));
        ButterKnife.bind(this, itemView);
    }

    public void showViewContent(T t) {
    }

    public Context getContext() {
        if (itemView == null) {
            return null;
        }
        return itemView.getContext();
    }
}