package com.a1magway.bgg.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.a1magway.bgg.R;
import com.a1magway.bgg.util.SDTimer;

/**
 * Created by enid on 2018/6/15.
 */

public class CountDownTextView extends android.support.v7.widget.AppCompatTextView {
    private Context mContext;

    private long mCountDownTime;

    private long mCountDownTimeTemp;

    private SDTimer mTimer = new SDTimer();

    private StringBuilder mStringBuilder = new StringBuilder();

    public CountDownTextView(Context context) {
        super(context);
        this.mContext = context;
    }

    public CountDownTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public CountDownTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public void startTickWork(long countDownTime, boolean onTop) {
        isOnTop(onTop);
        stopTickWork();
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

    public void isOnTop(boolean onTop) {
        setTextSize(16);
//        setPadding();
        if (onTop) {
            setTextColor(mContext.getResources().getColor(R.color.yellow));
//            setBackground(mContext.getResources().getDrawable(R.drawable.ic_count_down_top));
        } else {
            setTextColor(mContext.getResources().getColor(R.color.white));
//            setBackground(mContext.getResources().getDrawable(R.drawable.ic_count_down_normal));
        }
    }

    /**
     * 每秒钟调用一次
     */
    private void tickWork() {
        mCountDownTimeTemp --;
        this.setText(timeFormat(mCountDownTimeTemp));
        if (mCountDownTimeTemp <= 0) {
            stopTickWork();
        }
    }

    private String timeFormat(long time) {
        mStringBuilder.delete(0,mStringBuilder.length());
        long dd=time / 60 / 60 / 24;
        long hh = time/ 60 / 60 % 24;
        long mm = time / 60 % 60;
        long ss = time % 60;

        if (dd!=0){
            mStringBuilder.append(dd+"天 ");
        }
        if (hh<10) mStringBuilder.append("0");
        mStringBuilder.append(hh);
        mStringBuilder.append(":");
        if (mm<10) mStringBuilder.append("0");
        mStringBuilder.append(mm);
        mStringBuilder.append(":");
        if (ss<10)mStringBuilder.append("0");
        mStringBuilder.append(ss);
        return mStringBuilder.toString();
    }

    public void stopTickWork() {
        mTimer.stopWork();
        mCountDownTimeTemp = mCountDownTime;
    }
}
