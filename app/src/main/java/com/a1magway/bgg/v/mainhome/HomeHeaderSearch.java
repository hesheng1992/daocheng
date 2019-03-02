package com.a1magway.bgg.v.mainhome;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseViewOwner;
import com.a1magway.bgg.v.search.SearchActivity;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/** 首页顶部header的逻辑控制 Created by jph on 2017/7/24. */
public class HomeHeaderSearch extends BaseViewOwner {

    @BindView(R.id.home_txt_search)
    TextView mBannerView;

    public HomeHeaderSearch(@NonNull Context context) {
        super(context, R.layout.comm_search_title);
    }

    @OnClick(R.id.home_txt_search)
    public void onClickMoreSecKill(View v) {
        SearchActivity.start(v.getContext());
    }

    public void showBannerContent(List<String> urls, OnBannerListener onBannerListener) {

    }

}
