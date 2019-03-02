package com.a1magway.bgg.common;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.a1magway.bgg.App;
import com.a1magway.bgg.R;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.util.AppManager;
import com.a1magway.bgg.util.SoftInputUtil;
import com.a1magway.bgg.widget.dialog.LoadingDialogFragment;
import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 所有Activity界面的基类
 * Created by jph on 2017/7/19.
 */
public abstract class BaseActivity extends RxAppCompatActivity implements IActivity, ILoadingV {

    private FullLoadingHelper mFullLoadingHelper;
    private LoadingDialogFragment mLoadingDialog;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        if (getAnimType() != null) {
            switch (getAnimType()) {
                case ANIM_TYPE_RIGHT_IN:
                    overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                    break;
                case ANIM_TYPE_UP_IN:
                    overridePendingTransition(R.anim.slide_up_in, R.anim.slide_stay);
                    break;
                default:
                    break;
            }
        }
        setContentView(getContentViewLayoutId());

        initStatusBar();

        unbinder = ButterKnife.bind(this);

        mFullLoadingHelper = FullLoadingHelper.init(this);
        mLoadingDialog = new LoadingDialogFragment();

        initData(savedInstanceState);
        injectComponent(getApp().getAppComponent());
        initView(savedInstanceState);

        onCreateV(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
        unbinder.unbind();
        AppManager.getInstance().finishActivity(this);
    }

    public enum AnimType {
        ANIM_TYPE_RIGHT_IN, // 右侧滑动进入
        ANIM_TYPE_UP_IN;// 从页面底部进入
    }

    public void setNullStatus(int resoucesId){
        mFullLoadingHelper.setImgResouce(resoucesId);
    }
    /**
     * 页面切换动画
     *
     * @return
     */
    public AnimType getAnimType() {
        return null;
    }

    @LayoutRes
    public int getContentViewLayoutId() {
        return 0;
    }

    @Override
    public void onCreateV(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
    }

    @Override
    public void showFullLoading() {
        mFullLoadingHelper.showFullLoading();
    }

    @Override
    public void hideFullLoading() {
        mFullLoadingHelper.hideFullLoading();
    }

    @Override
    public void showNoneLayout() {
        mFullLoadingHelper.showNoneLayout();
    }

    @Override
    public void hideNoneLayout() {
        mFullLoadingHelper.hideNoneLayout();
    }
    @Override
    public void hideLoginLoading(){
        mFullLoadingHelper.hideLoginLoading();
    }

    @Override
    public void showNoneLayout4NotLogin() {
        mFullLoadingHelper.showNoneLayout4NotLogin();
    }

    @Override
    public void showLoadingDialog() {
        mLoadingDialog.show(getSupportFragmentManager(), "LoadingDialog");
    }

    @Override
    public void showLoadingDialog(String msg) {
        mLoadingDialog.show(getSupportFragmentManager(), "LoadingDialog");
    }


    @Override
    public void hideLoadingDialog() {
        if(mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    public App getApp() {
        return (App) getApplication();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {

    }

    public void finishWithAnim() {
        if (getAnimType() != null) {
            switch (getAnimType()) {
                case ANIM_TYPE_RIGHT_IN:
                    finishWithAnimRightOut();
                    break;
                case ANIM_TYPE_UP_IN:
                    finishWithAnimDownOut();
                    break;
                default:
                    finish();
                    break;
            }
        } else {
            finish();
        }
    }

    private void finishWithAnimRightOut() {
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    private void finishWithAnimDownOut() {
        finish();
        overridePendingTransition(0, R.anim.slide_down_out);
    }
    /**
     * 返回键
     */
    public void onBackButtonClick() {
        SoftInputUtil.hideSoftInput(this);
        if (preExitPage()) {
            return;
        }
        finishWithAnim();
    }

    protected boolean preExitPage() {
        return false;
    }

    @Override
    public void onBackPressed() {
        SoftInputUtil.hideSoftInput(this);
        if (preExitPage()) {
            return;
        }
        finishWithAnim();
        super.onBackPressed();
    }

    public <T> LifecycleTransformer<T> bindToDestroyEvent() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }

    private void initStatusBar() {

        ImmersionBar immersionBar = ImmersionBar.with(this);
        immersionBar.fitsSystemWindows(!fitsSystemWindows());
        if (!fitsSystemWindows()) {
            immersionBar.statusBarColor(R.color.white);
        }
        immersionBar.statusBarDarkFont(true);
        immersionBar.init();
    }

    protected boolean fitsSystemWindows() {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
