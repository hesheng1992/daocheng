package com.a1magway.bgg.refactor;

import android.content.Context;
import android.util.AttributeSet;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * author: Beaven
 * date: 2017/10/27 17:50
 */

public class AppRefreshLayout extends SmartRefreshLayout {

    public AppRefreshLayout(Context context) {
        this(context, null);
    }


    public AppRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public AppRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setDisableContentWhenRefresh(true);
        setDisableContentWhenLoading(true);
    }
}
