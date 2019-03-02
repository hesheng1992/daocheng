package com.a1magway.bgg.p.cart;

import android.support.annotation.NonNull;

/**
 * 管理购物车总数的工具类
 * Created by jph on 2017/8/15.
 */
public class CountHelper {

    private int mMinCount;
    private int mMaxCount;

    private int mCount = 0;

    private OnCountChangeListener mOnCountChangeListener;

    public static CountHelper create(int minCount, int maxCount,
                                     @NonNull OnCountChangeListener onCountChangeListener) {
        return new CountHelper(minCount, maxCount, onCountChangeListener);
    }

    public CountHelper(int minCount, int maxCount, OnCountChangeListener onCountChangeListener) {
        mMinCount = minCount;
        mMaxCount = maxCount;
        mOnCountChangeListener = onCountChangeListener;
    }

    public void init(int count) {
        if (count < mMinCount) {
            mCount = mMinCount;
            mOnCountChangeListener.onCountChange(mCount);
        } else if (count > mMaxCount) {
            mCount = mMaxCount;
            mOnCountChangeListener.onCountChange(mCount);
        } else {
            mCount = count;
            if (mCount == mMinCount) {
                mOnCountChangeListener.onReduceEnableChange(false);
            } else if (mCount == mMaxCount) {
                mOnCountChangeListener.onPlusEnableChange(false);
            }
        }
    }

    public void plus() {
        int newCount = mCount + 1;
        if (mCount == mMaxCount) {
            return;
        }

        if (newCount >= mMaxCount) {
            newCount = mMaxCount;
            if (mCount != mMaxCount) {
                //之前未达到最大限制
                mOnCountChangeListener.onPlusEnableChange(false);
            }
        }

        if (mCount == mMinCount) {
            //之前达到了最小限制
            mOnCountChangeListener.onReduceEnableChange(true);
        }

        mCount = newCount;
        mOnCountChangeListener.onCountChange(mCount);
    }

    public void reduce() {
        int newCount = mCount - 1;
        if (mCount == mMinCount) {
            return;
        }

        if (newCount <= mMinCount) {
            newCount = mMinCount;
            if (mCount != mMinCount) {
                //之前未达到最小限制
                mOnCountChangeListener.onReduceEnableChange(false);
            }
        }

        if (mCount == mMaxCount) {
            //之前达到了最大限制
            mOnCountChangeListener.onPlusEnableChange(true);
        }

        mCount = newCount;
        mOnCountChangeListener.onCountChange(mCount);
    }

    public interface OnCountChangeListener {

        void onPlusEnableChange(boolean enable);

        void onReduceEnableChange(boolean enable);

        void onCountChange(int newCount);
    }
}
