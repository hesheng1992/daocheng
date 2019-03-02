package com.a1magway.bgg.v.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PFragment;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerHomeComponent;
import com.a1magway.bgg.di.module.HomeModule;
import com.a1magway.bgg.p.home.HomeP;
import com.a1magway.bgg.v.main.MainActivity;
import com.a1magway.bgg.v.search.SearchActivity;
import com.a1magway.bgg.widget.LoadMoreRecyclerView;
import com.a1magway.bgg.widget.OldRefreshLayout;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 首页
 * Created by jph on 2017/7/20.
 */
public class HomeFragment extends PFragment<HomeP> implements IHomeV {

    @Inject
    HomeHeaderVO mHomeHeaderVO;

    @BindView(R.id.home_layout_refresh)
    OldRefreshLayout mOldRefreshLayout;
    @BindView(R.id.home_recycler)
    LoadMoreRecyclerView mRecyclerView;

    @Override
    public int getContentViewLayoutId() {
        return R.layout.home_fragment_home;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);

        DaggerHomeComponent.builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onCreateV(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.onCreateV(savedInstanceState, contentView);

        mRecyclerView.addHeaderView(mHomeHeaderVO.getView());
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);

        MainActivity.setNullStatusBarHeight(contentView);

        mOldRefreshLayout.disableWhenHorizontalMove(true);
        mOldRefreshLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPresenter.reload();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setLoadable(false);
    }

    @Override
    public void onStart() {
        super.onStart();

        mHomeHeaderVO.startAutoPlayBanner();
    }

    @Override
    public void onStop() {
        super.onStop();

        mHomeHeaderVO.stopAutoPlayBanner();
    }


    @OnClick(R.id.home_txt_search)
    public void onClickSearch(View v) {
        SearchActivity.start(v.getContext());
    }

    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showBannerContent(List<String> urls, OnBannerListener onBannerListener) {
        mHomeHeaderVO.showBannerContent(urls, onBannerListener);
    }

    @Override
    public void showSeKillContent(BaseRecyclerAdapter adapter) {
        mHomeHeaderVO.showSeKillContent(adapter);
    }

    @Override
    public void showSeckillCountdown(String time) {
        mHomeHeaderVO.setSeckillCountdown(time);
    }

    @Override public void showMemberEnterLarge(boolean isShow) {
        mHomeHeaderVO.showMemberEnterLarge(isShow);
    }

    @Override
    public void stopRefresh() {
        mOldRefreshLayout.refreshComplete();
    }

    @Override
    public TextView getCountView() {
        return mHomeHeaderVO.getTextCountdown();
    }
}
