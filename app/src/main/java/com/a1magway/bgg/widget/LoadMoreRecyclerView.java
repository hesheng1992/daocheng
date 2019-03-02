package com.a1magway.bgg.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.a1magway.bgg.R;
import com.xxxrecylcerview.XXXRecyclerView;

/**
 * Created by jph on 2017/8/2.
 */
public class LoadMoreRecyclerView extends XXXRecyclerView {
    public LoadMoreRecyclerView(Context context) {
        super(context);
        init();
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLoadMoreView(View.inflate(getContext(), R.layout.v_load_more, null));
    }
}
