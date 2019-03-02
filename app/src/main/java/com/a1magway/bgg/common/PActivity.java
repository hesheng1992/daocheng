package com.a1magway.bgg.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.a1magway.bgg.refactor.BaseContract;
import javax.inject.Inject;

/**
 * Created by jph on 2017/8/7.
 */
public class PActivity<P extends BaseContract.BasePresenter> extends BaseActivity {

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (mPresenter != null) {
            mPresenter.onCreate();
        }
    }

    @Override
    public void onCreateV(@Nullable Bundle savedInstanceState) {
        super.onCreateV(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}
