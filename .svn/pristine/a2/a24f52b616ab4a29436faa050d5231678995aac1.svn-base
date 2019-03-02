package com.a1magway.bgg.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by enid on 2018/7/30.
 */

public class NoSwitchViewPager extends ViewPager {
    public NoSwitchViewPager(Context context) {
        super(context);
    }

    public NoSwitchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);
        //禁止viewpager滑动
        return false;
    }
}
