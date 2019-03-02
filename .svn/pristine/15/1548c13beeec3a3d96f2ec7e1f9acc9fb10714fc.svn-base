package com.a1magway.bgg.refactor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import javax.inject.Inject;

/**
 * author: Beaven
 * date: 2017/10/30 10:17
 */

@SuppressLint("Registered") public class PresenterActivity<P extends BaseContract.BasePresenter>
    extends AnimTransitionActivity {

    @Inject
    protected P presenter;


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter != null) {
            presenter.onCreate();
        }
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }
}
