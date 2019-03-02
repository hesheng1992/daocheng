package com.a1magway.bgg.common;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a1magway.bgg.App;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.widget.dialog.LoadingDialogFragment;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 所有Fragment的基类
 * Created by jph on 2017/7/19.
 */
public abstract class BaseFragment extends RxFragment implements IFragment, ILoadingV {

    private FullLoadingHelper mFullLoadingHelper;
    private LoadingDialogFragment mLoadingDialog;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(getContentViewLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, contentView);


        mFullLoadingHelper = FullLoadingHelper.init(contentView, getActivity());

        mLoadingDialog = new LoadingDialogFragment();
        
//        initData(savedInstanceState);//enid:移动到initView下面
        injectComponent(getApp().getAppComponent());
        initView(savedInstanceState, contentView);
        initData(savedInstanceState);
        onCreateV(savedInstanceState, contentView);

        return contentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @LayoutRes
    public int getContentViewLayoutId() {
        return 0;
    }

    @Override
    public void onCreateV(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
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
    public void hideLoginLoading(){
        mFullLoadingHelper.hideLoginLoading();
    }
    @Override
    public void hideNoneLayout() {
        mFullLoadingHelper.hideNoneLayout();
    }

    @Override
    public void showNoneLayout4NotLogin() {
        mFullLoadingHelper.showNoneLayout4NotLogin();
    }

    @Override
    public void showLoadingDialog() {
        mLoadingDialog.show(getChildFragmentManager(), "LoadingDialog");
    }

    @Override
    public void showLoadingDialog(String msg) {
        mLoadingDialog.show(getChildFragmentManager(), "LoadingDialog");
    }

    @Override
    public void hideLoadingDialog() {
        mLoadingDialog.dismiss();
    }

    public App getApp() {
        if (getActivity() == null) {
            return null;
        }
        return (App) getActivity().getApplication();
    }

    @Override
    public void injectComponent(AppComponent appComponent) {

    }

    public <T> LifecycleTransformer<T> bindToDestroyEvent() {
        return bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }
}