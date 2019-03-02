package com.almagway.common.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 处理了绑定到RecyclerView上的回调,使用对应的Adapter必须是{@link RecyclerAttachViewBinder}
 * Created by jph on 2017/7/27.
 */
public abstract class RecyclerAttachVH extends RecyclerView.ViewHolder {

    public RecyclerAttachVH(View itemView) {
        super(itemView);
    }

    public abstract void onViewAttachedToWindow();

    public abstract void onViewDetachedFromWindow();

}
