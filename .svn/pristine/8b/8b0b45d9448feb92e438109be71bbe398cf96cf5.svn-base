package com.a1magway.bgg.widget.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.a1magway.bgg.common.BaseDialogFragment;

/**
 * 从底部弹出的弹窗
 * Created by jph on 2017/8/21.
 */
public abstract class BottomDialogFragment extends BaseDialogFragment {

    private boolean mIsAniming = false;
//    private View mRootView;

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.getDecorView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();
                return true;
            }
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomAnimUtil.slideToUp(view);
    }

    @Override
    public void dismiss() {
        if (mIsAniming) {
            return;
        }
        mIsAniming = true;
        BottomAnimUtil.slideToDown(getView(), new BottomAnimUtil.AnimationListener() {
            @Override
            public void onFinish() {
                mIsAniming = false;
                BottomDialogFragment.super.dismiss();
            }
        });
    }
}
