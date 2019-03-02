package com.a1magway.bgg.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;

/**
 * Created by enid on 2018/8/19.
 */

public class PingtuanBannerIndicator extends LinearLayout {
    public PingtuanBannerIndicator(Context context) {
        this(context, null);
    }

    public PingtuanBannerIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PingtuanBannerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private View getIndicatorView(Context context) {
        TextView textView = new TextView(context);
        LayoutParams tv_layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        tv_layoutParams.weight = 1;
        textView.setBackground(ContextCompat.getDrawable(context, R.drawable.indicator_pingtuan));
        textView.setSelected(false);
        tv_layoutParams.setMargins(10, 0, 10, 0);
        textView.setLayoutParams(tv_layoutParams);
        return textView;
    }

    private void addIndicatorView(int size, Context context) {
        removeAllViews();
        for (int i = 0; i < size; i++) {
            addView(getIndicatorView(context));
        }
    }

    /**
     *设置当前选中的页
     */
    public void setCurrentSelecte(int current) {
        TextView tv;
        for (int i = 0; i < getChildCount(); i++) {
            tv = (TextView) getChildAt(i);
            if (i == current) {
                tv.setSelected(true);
            } else {
                tv.setSelected(false);
            }
        }
    }

    /**
     *初始化指示器
     */
    public void init(Context context, int childCount) {
        ViewGroup.LayoutParams lly_layoutParams = getLayoutParams();
        setOrientation(LinearLayout.HORIZONTAL);
        lly_layoutParams.height = 5;//指示器高度
        switch (childCount) {
            case 1:
                lly_layoutParams.width = 100;
                addIndicatorView(1, context);
                break;
            case 2:
                lly_layoutParams.width = 200;
                addIndicatorView(2, context);
                break;
            case 3:
                lly_layoutParams.width = 300;
                addIndicatorView(3, context);
                break;
            case 4:
                lly_layoutParams.width = 400;
                addIndicatorView(3, context);
                break;
            default:
                lly_layoutParams.width = 500;
                addIndicatorView(childCount, context);
                break;
        }
        setLayoutParams(lly_layoutParams);
        requestLayout();
    }
}
