package com.a1magway.bgg.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 兼容在RecyclerView也可以滑动的EditText
 * Created by jph on 2017/8/23.
 */
public class EditInRecycler extends AppCompatEditText {
    public EditInRecycler(Context context) {
        super(context);
    }

    public EditInRecycler(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditInRecycler(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (canScrollVertically(-1)) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.dispatchTouchEvent(event);
    }
}
