package com.a1magway.bgg.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.util.SDTimer;

/**
 * Created by enid on 2018/6/15.
 */

public class ThemeCountDownTextView extends android.support.v7.widget.AppCompatTextView {

    private Context mContext;

    private long mCountDownTime;

    private long mCountDownTimeTemp;

    private SDTimer mTimer = new SDTimer();

    private StringBuilder mStringBuilder = new StringBuilder();

    public ThemeCountDownTextView(Context context) {
        super(context);
        this.mContext = context;
    }

    public ThemeCountDownTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public ThemeCountDownTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public void startTickWork(long countDownTime) {
        mCountDownTime = countDownTime;
        mCountDownTimeTemp = countDownTime / 1000;
        mTimer.startWork(0, 1000, new SDTimer.SDTimerListener() {

            @Override
            public void onWorkMain() {
                tickWork();
            }

            @Override
            public void onWork() {
            }
        });
    }


    public long getCountDownTime() {
        return mCountDownTimeTemp * 1000;
    }


    /**
     * 每秒钟调用一次
     */
    private void tickWork() {
        mCountDownTimeTemp--;
        this.setText(timeFormat(mCountDownTimeTemp));
        if (mCountDownTimeTemp <= 0) {
            stopTickWork();
        }
    }

    private String timeFormat(long time) {
        mStringBuilder.delete(0, mStringBuilder.length());
        if (time < 0) time = 0;//避免服务器返回时间不对，显示为负数的情况
        long dd = time / 60 / 60 / 24;
        long hh = time / 60 / 60 % 24;
        long mm = time / 60 % 60;
        long ss = time % 60;
        String head = "剩余:";//活动剩余时间:
        String d = "天";
        String h = ":";//小时
        String m = ":";//分
        String s = "";//秒
        mStringBuilder.append(head);
        if (dd != 0) {
            mStringBuilder.append(dd + d);
        }
        if (hh < 10) mStringBuilder.append("0");
        mStringBuilder.append(hh);
        mStringBuilder.append(h);
        if (mm < 10) mStringBuilder.append("0");
        mStringBuilder.append(mm);
        mStringBuilder.append(m);
        if (ss < 10) mStringBuilder.append("0");
        mStringBuilder.append(ss);
        mStringBuilder.append(s);
        return mStringBuilder.toString();
    }


    public void stopTickWork() {
        mTimer.stopWork();
        mCountDownTimeTemp = mCountDownTime;
    }
}
