package com.a1magway.bgg.v.found;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.a1magway.bgg.R;
import com.a1magway.bgg.refactor.AnimTransitionActivity;
import com.a1magway.bgg.util.AndroidWorkaroundUtil;

import butterknife.BindView;

/**
 * 通用的webActivity
 * Created by enid on 2018/8/23.
 */

public class GeneralWebActivity extends AnimTransitionActivity {
    private static final String TITLE = "title";
    private static final String UTL = "url";
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    private String title;
    private String url;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_web_general;
    }

    public static void start(Context context, String url, String title) {
        Intent intent = new Intent(context, GeneralWebActivity.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(UTL, url);
        context.startActivity(intent);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        title = getIntent().getStringExtra(TITLE);
        url = getIntent().getStringExtra(UTL);
        setTextTitle(title);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, FoundFragment.newInstance(url));
        transaction.commit();
        AndroidWorkaroundUtil.assistActivity(getActivity());
    }
}
