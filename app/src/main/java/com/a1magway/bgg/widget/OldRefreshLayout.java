package com.a1magway.bgg.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.a1magway.bgg.R;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 下拉刷新的Layout
 * Created by jph on 2017/7/24.
 */
public class OldRefreshLayout extends PtrFrameLayout {
    public OldRefreshLayout(Context context) {
        super(context);
        init();
    }

    public OldRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OldRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setHeaderView(View.inflate(getContext(), R.layout.v_refreshing, null));
        setResistance(1.7f);
        setRatioOfHeaderHeightToRefresh(1.2f);
        setDurationToClose(200);
        setDurationToCloseHeader(1000);
        setPullToRefresh(false);
        setKeepHeaderWhenRefresh(true);
    }
}
