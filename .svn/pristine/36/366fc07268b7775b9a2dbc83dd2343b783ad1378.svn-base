package com.a1magway.bgg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.a1magway.bgg.refactor.BaseNewActivity;

/**
 * 用来测试的Activity
 */
public class TestActivity extends BaseNewActivity {

    @Override public int getContentViewLayoutId() {
        return R.layout.activity_test;
    }


    @Override public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTextTitle("测试页面");
    }


    private boolean isShow = true;


    public void swtch(View view) {
        if (isShow) {
            hideTitle();
            isShow = false;
        } else {
            showTitle();
            isShow = true;
        }
    }
}
