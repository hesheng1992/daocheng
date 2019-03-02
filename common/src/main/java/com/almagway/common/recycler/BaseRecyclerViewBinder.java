package com.almagway.common.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.almagway.common.inter.ItemClickListener;

/**
 * author: Beaven
 * date: 2017/10/25 20:52
 */

public abstract class BaseRecyclerViewBinder<T, VH extends RecyclerView.ViewHolder> extends
    RecyclerRxLifeViewBinder<T, VH> {

    private ItemClickListener clickListener;


    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }


    @Override
    protected void onBindViewHolder(@NonNull final VH holder, @NonNull T item) {
        if (clickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.click(v, getPosition(holder));
                }
            });
        }
    }
}
