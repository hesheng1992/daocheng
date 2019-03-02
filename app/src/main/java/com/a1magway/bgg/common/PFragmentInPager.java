package com.a1magway.bgg.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.a1magway.bgg.refactor.BaseContract;
import javax.inject.Inject;

/**
 * Created by jph on 2017/8/7.
 */
public class PFragmentInPager<P extends BaseContract.BasePresenter> extends FragmentInPager {
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
