package com.a1magway.bgg.v.pintuan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerPingtuanComponent;
import com.a1magway.bgg.di.module.PintuanModule;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.p.pintuan.PingtuanProductP;
import com.a1magway.bgg.refactor.AppRefreshLayout;
import com.a1magway.bgg.v.mainhome.HomeRecommendHeader;
import com.a1magway.bgg.widget.LoadMoreRecyclerView;
import com.a1magway.bgg.widget.TitleBar;
import com.a1magway.bgg.widget.banner.BannerView;
import com.almagway.common.utils.ScreenUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by enid on 2018/8/22.
 */

public class PingtuanActivity extends PActivity<PingtuanProductP> implements PingtuanProductContract.View {
    @BindView(R.id.special_product_refresh)
    AppRefreshLayout mRefreshLayout;

    @BindView(R.id.special_product_recyclerview)
    LoadMoreRecyclerView mRecyclerView;

    @BindView(R.id.special_product_title_bar)
    TitleBar mTitleBar;

    @BindView(R.id.banner)
    BannerView bannerView;

    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;

    public static void start(Context context) {
        Intent intent = new Intent(context, PingtuanActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerPingtuanComponent.builder()
                .appComponent(appComponent)
                .pintuanModule(new PintuanModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTitleBar.setDefLeftImgClickListener(this);
        mTitleBar.setTitleTxt("拼团商品");

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                LogUtil.e("ssssssssss", "gggggggggggg");
                mPresenter.reload();
            }
        });

        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                LogUtil.e("ssssssssss", "1111111111");
                mPresenter.loadMore();
            }
        });
        nestedScroll();
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_special_product;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        HomeRecommendHeader homeRecommendHeader = new HomeRecommendHeader(this, new HomeRecommendHeader.OrderByChangeListener() {
            @Override
            public void onOrderBy(int order_by, int asc_desc) {
                mPresenter.executeFilter(order_by, asc_desc);
            }

        }, false);
        mRecyclerView.addHeaderView(homeRecommendHeader.getView());
        //应外层是scrollerView，滑动冲突会卡顿，所以设置recyclerView不能滑动，避免滑动不流畅
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int itemCount = parent.getLayoutManager().getItemCount();
                int currentItem = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
                if (currentItem % 2 == 0) {// 第一列
                    if (currentItem >= 2) {
                        outRect.left = ScreenUtil.dp2px(8f);
                        outRect.right = ScreenUtil.dp2px(16f);
                    }
                } else if (currentItem % 2 == 1) { //最后列
                    outRect.left = ScreenUtil.dp2px(16f);
                    outRect.right = ScreenUtil.dp2px(8f);
                }
                outRect.bottom = ScreenUtil.dp2px(10f);//最后一列--
            }
        });
        mRecyclerView.setLoadable(false);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setTitle(String title, long time) {

    }

    @Override
    public void stopRefresh() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishRefresh();
        }
    }

    @Override
    public void stopLoadMore() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishLoadmore();
        }
    }

    @Override
    public void startRefresh() {
        if (mRefreshLayout != null) {
            mRefreshLayout.autoRefresh();
        }
    }

    @Override
    public void setLoadable(boolean loadable) {

    }

    @Override
    public String getComeFrom() {
        return "拼团";
    }

    @Override
    public void setBannerData(List<String> banners) {

    }

    //兼容滑动问题
    private void nestedScroll() {
        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                mRefreshLayout.setEnableRefresh(nestedScrollView.getScrollY() == 0);
            }
        });
    }

}
