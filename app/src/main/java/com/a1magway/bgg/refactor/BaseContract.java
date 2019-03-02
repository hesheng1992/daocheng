package com.a1magway.bgg.refactor;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.a1magway.bgg.di.component.AppComponent;

/**
 * author: Beaven
 * date: 2017/10/31 11:45
 */

public interface BaseContract {

    interface BaseView {

        void initData(@Nullable Bundle savedInstanceState);

        void injectComponent(AppComponent appComponent);

        void initView(@Nullable Bundle savedInstanceState);

        void onCreateV(@Nullable Bundle savedInstanceState);

        Activity getActivity();
    }


    interface BasePresenter {

        void onCreate();

        void onDestroy();
    }


    interface BaseLoading {

        void showLoading(@Nullable String text);

        void hideLoading();

        void showError();

        void showEmpty();
    }


    interface BaseLoadView extends BaseView, BaseLoading {}
}
