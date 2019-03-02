package com.a1magway.bgg.common;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.v.login.LoginActivity;

import butterknife.ButterKnife;

/**
 * 整页加载显示的帮助类,若要有效使用，需要使用的布局文件中include R.layout.v_full_loading
 * Created by jph on 2017/7/21.
 */
public class FullLoadingHelper {

    private ViewStub mViewStub;
    private View mRootLayout;

    private ViewGroup mFullLoadingLayout;
    private ViewGroup mNoneLayout;
    private TextView button;
    private Context mContext;
    private ImageView mImageView;
    private int resoucesId;
    private FullLoadingHelper(Activity activity) {
        mContext = activity;
        mViewStub = ButterKnife.findById(activity, R.id.full_loading_stub);
    }

    private FullLoadingHelper(View containerView) {
        mViewStub = ButterKnife.findById(containerView, R.id.full_loading_stub);
    }
    private FullLoadingHelper(View containerView, Activity activity) {
        mContext = activity;
        mViewStub = ButterKnife.findById(containerView, R.id.full_loading_stub);
    }

    /**
     * @param activity FullLoading所处的Activity
     */
    public static FullLoadingHelper init(Activity activity) {
        return new FullLoadingHelper(activity);
    }

    /**
     * @param containerView FullLoading所处的容器View
     */
    public static FullLoadingHelper init(View containerView) {
        return new FullLoadingHelper(containerView);
    }

    /**
     * @param containerView FullLoading所处的容器View带有context
     */
    public static FullLoadingHelper init(View containerView, Activity activity) {
        return new FullLoadingHelper(containerView, activity);
    }

    public void showFullLoading() {
        if (isDisable()) {
            return;
        }
        checkInflate();

        mFullLoadingLayout.setVisibility(View.VISIBLE);
        mNoneLayout.setVisibility(View.GONE);
    }

    public void showNoneLayout() {
        Log.i("tag","showNoneLayout");
        if (isDisable()) {
            return;
        }
        checkInflate();

        mFullLoadingLayout.setVisibility(View.GONE);
        button.setVisibility(View.GONE);
        mNoneLayout.setVisibility(View.VISIBLE);
        if (resoucesId==R.mipmap.null_address){
            mImageView=ButterKnife.findById(mRootLayout,R.id.null_status_img);
            mImageView.setImageResource(R.mipmap.null_address);
        }

    }

    public void hideNoneLayout() {
        if (mNoneLayout != null) {
            mNoneLayout.setVisibility(View.GONE);
        }
    }

    public void setImgResouce(int resouceId){
        this.resoucesId=resouceId;
    }

    public void showNoneLayout4NotLogin() {
        Log.i("tag","showNoneLayout4NotLogin");
        if (isDisable()) {
            return;
        }
        checkInflate();
        button.setVisibility(View.VISIBLE);
        mFullLoadingLayout.setVisibility(View.GONE);
        mNoneLayout.setVisibility(View.VISIBLE);
    }

    public void hideFullLoading() {
        if (mFullLoadingLayout != null) {
            mFullLoadingLayout.setVisibility(View.GONE);
        }
    }

    public void hideLoginLoading(){
        if (button!=null){
            button.setVisibility(View.GONE);
        }
    }

    private void checkInflate() {
        if (mRootLayout == null) {
            mRootLayout = mViewStub.inflate();
            initView();
        }
    }

    /**
     * 布局文件中无整页显示的include，则不支持该类
     *
     * @return
     */
    private boolean isDisable() {
        return mViewStub == null;
    }

    private void initView() {
        mFullLoadingLayout = ButterKnife.findById(mRootLayout, R.id.full_loading_layout_full_loading);
        mNoneLayout = ButterKnife.findById(mRootLayout, R.id.full_loading_layout_none);
        //TODO 可再添加整页错误显示，重试按钮等
         button = ButterKnife.findById(mRootLayout,R.id.cart_login_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.start(mContext);
            }
        });
    }
}
