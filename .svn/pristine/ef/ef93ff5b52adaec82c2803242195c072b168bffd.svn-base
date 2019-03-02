package com.a1magway.bgg.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.a1magway.bgg.refactor.BaseContract;
import javax.inject.Inject;

/**
 * 使用泛型统一调用了presenter生命周期的Fragment基类
 * Created by jph on 2017/8/7.
 */
public abstract class PFragment<P extends BaseContract.BasePresenter> extends BaseFragment {

    @Inject
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        if (mPresenter != null) {
            mPresenter.onCreate();
        }

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}
