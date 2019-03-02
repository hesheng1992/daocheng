package com.a1magway.bgg.util;

/**
 * 双击处理
 * Created by jph on 2016/7/20.
 */
public class DoubleClick {

    private DoubleClickListener mDoubleClickListener;
    private long mClickMomentMills = -1;

    public DoubleClick(DoubleClickListener doubleClickListener) {
        mDoubleClickListener = doubleClickListener;
    }

    public void click() {
        if (mClickMomentMills != -1 && System.currentTimeMillis() - mClickMomentMills < 2500) {
            mDoubleClickListener.onSecondClick();
            mClickMomentMills = -1;
        } else {
            mClickMomentMills = System.currentTimeMillis();
            mDoubleClickListener.onFirstClick();
        }
    }

    /**
     * 双击监听
     */
    public interface DoubleClickListener {
        /**
         * 第一次点击
         */
        void onFirstClick();

        /**
         * 第二次点击
         */
        void onSecondClick();
    }
}
