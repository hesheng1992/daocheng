package com.a1magway.bgg.widget.divider;

import android.content.Context;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;

/**
 * 针对RecyclerView GridLayoutManager的分割
 * Created by jph on 2016/12/18.
 */
public class GridItemDecoration extends com.jph.xxxrecyclerviewdivider.GridItemDecoration {
    public GridItemDecoration(Context context, @DrawableRes int horizontalDrawRes, @DrawableRes int verticalDrawRes, @DimenRes int dividerWidthRes, @DimenRes int dividerHeightRes) {
        super(context, horizontalDrawRes, verticalDrawRes, dividerWidthRes, dividerHeightRes);
    }

    public GridItemDecoration(Context context, @DrawableRes int drawRes, @DimenRes int dividerSizeRes) {
        super(context, drawRes, dividerSizeRes);
    }

    public GridItemDecoration(Context context, @DrawableRes int drawRes, @DimenRes int dividerWidthRes, @DimenRes int dividerHeightRes) {
        super(context, drawRes, dividerWidthRes, dividerHeightRes);
    }
}
