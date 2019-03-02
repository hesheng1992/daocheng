package com.a1magway.bgg.widget.divider;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * 针对RecyclerView LinearLayoutManager的分割
 * Created by jph on 2016/12/18.
 */
public class LinearItemDecoration extends com.jph.xxxrecyclerviewdivider.LinearItemDecoration {
    public LinearItemDecoration(Context context, @DrawableRes int drawRes, @DimenRes int sizeRes) {
        super(context, drawRes, sizeRes);
    }

    public LinearItemDecoration(@NonNull Drawable divider, int dividerSize) {
        super(divider, dividerSize);
    }

//    @Override
//    protected boolean isAllowShowDivider(int position, RecyclerView parent) {
//        if (!isNormalItem(position, parent.getAdapter())) {
//            return false;
//        }
//
//        return super.isAllowShowDivider(position, parent);
//    }
//
//    /**
//     * 是否是正常的item
//     *
//     * @param position
//     * @param adapter
//     * @return
//     */
//    private boolean isNormalItem(int position, RecyclerView.Adapter adapter) {
//        if (adapter == null || !(adapter instanceof BaseRecyclerViewBinder)) {
//            return true;
//        }
//
//        CZRecyclerAdapter ultimateViewAdapter = (CZRecyclerAdapter) adapter;
//        return ultimateViewAdapter.isNormalItemType(position);
//    }
}
