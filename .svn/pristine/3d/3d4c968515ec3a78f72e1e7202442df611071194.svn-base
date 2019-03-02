package com.a1magway.bgg.v.member;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.member.DaggerMemberGoodsComponent;
import com.a1magway.bgg.di.module.member.MemberGoodsModule;
import com.a1magway.bgg.p.member.MemberGoodsP;
import com.a1magway.bgg.refactor.AppRefreshLayout;
import com.a1magway.bgg.refactor.LoadingView;
import com.a1magway.bgg.refactor.PresenterActivity;
import com.a1magway.bgg.widget.divider.GridItemDecoration;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

public class MemberGoodsActivity extends PresenterActivity<MemberGoodsP>
        implements MemberGoodsContract.View {

    @BindView(R.id.layout_refresh_member_goods)
    AppRefreshLayout refreshLayout;
    @BindView(R.id.recycler_member_goods)
    RecyclerView mRecyclerView;
    @BindView(R.id.view_load)
    LoadingView loadingView;


    public static void start(Context context) {
        Intent intent = new Intent(context, MemberGoodsActivity.class);
        context.startActivity(intent);
    }


    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_member_goods;
    }


    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerMemberGoodsComponent.builder()
                .appComponent(appComponent)
                .memberGoodsModule(new MemberGoodsModule(this))
                .build().inject(this);
    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTextTitle(R.string.members_only_goods);
        loadingView.showContent();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.addItemDecoration(new GridItemDecoration(this, R.color.transparent,
                R.dimen.seckill_divider_h, R.dimen.seckill_divider_v));

        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                presenter.loadMore();
            }


            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.reload();
            }
        });
    }


    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    public void stopRefresh() {
        refreshLayout.finishRefresh();
    }


    @Override
    public void stopLoadMore(boolean success) {
        refreshLayout.finishLoadmore(success);
    }


    @Override
    public void setLoadable(boolean loadable) {
        refreshLayout.setLoadmoreFinished(loadable);
    }


    @Override
    public void showLoading(@Nullable String text) {
    }


    @Override
    public void hideLoading() {
        if (loadingView != null) {
            loadingView.showContent();
        }
    }


    @Override
    public void showError() {
        loadingView.showError();
    }


    @Override
    public void showEmpty() {
        loadingView.showEmpty();
    }
}
