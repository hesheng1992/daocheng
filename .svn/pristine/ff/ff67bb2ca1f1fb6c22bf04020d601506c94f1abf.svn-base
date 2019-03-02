package com.a1magway.bgg.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.a1magway.bgg.di.component.AppComponent;

/**
 * Created by jph on 2017/8/7.
 */
public interface IFragment {

    /**
     * 在此方法注入数据
     *
     * @param appComponent
     */
    void injectComponent(AppComponent appComponent);

    void onCreateV(@Nullable Bundle savedInstanceState, @Nullable View contentView);

    void initData(@Nullable Bundle savedInstanceState);

    void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView);
}
