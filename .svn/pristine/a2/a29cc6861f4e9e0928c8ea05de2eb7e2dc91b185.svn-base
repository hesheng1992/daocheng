package com.a1magway.bgg.v.mainhome;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.mainhome.DaggerSpecialProductComponent;
import com.a1magway.bgg.di.module.mainhome.SpecialProductModule;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.p.mainhome.SpecialProductP;
import com.a1magway.bgg.refactor.AppRefreshLayout;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.widget.LoadMoreRecyclerView;
import com.a1magway.bgg.widget.TitleBar;
import com.a1magway.bgg.widget.banner.BannerView;
import com.almagway.common.utils.ScreenUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xxxrecylcerview.XXXRecyclerView;
import com.youth.banner.Transformer;

import java.util.List;

import butterknife.BindView;

/**
 * 首页主题详情产品列表
 */
public class SpecialProductActivity extends PActivity<SpecialProductP> implements SpecialProductContract.View {

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

    private static final String EXTRA_SPECIAL_ID = "extra_special_id";
    private static final String COME_FROM_NAME = "come_from_name";
    private static final String EXTRA_TITLE = "extra_title";

    /**
     * 主题id,用于查询主题详情
     */
    private int mId;

    //来自哪个种类（本期推荐，新品，特卖会，下期预告）
    private String comeFrom;

    private HomeRecommendHeader homeRecommendHeader;

    private String title;


    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_special_product;
    }

    public static final void start(Context context, int id, String comeFrom, String title) {
        Intent intent = new Intent(context, SpecialProductActivity.class);
        intent.putExtra(COME_FROM_NAME, comeFrom);
        intent.putExtra(EXTRA_SPECIAL_ID, id);
        intent.putExtra(EXTRA_TITLE, title);
        JumpUtil.startActivity(context, intent);
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerSpecialProductComponent.builder()
                .appComponent(appComponent)
                .specialProductModule(new SpecialProductModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTitleBar.setDefLeftImgClickListener(this);
        mTitleBar.setTitleTxt(title);

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

//        mRecyclerView.setOnLoadMoreListener(new XXXRecyclerView.OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                mPresenter.loadMore();
//            }
//        });
//        mRefreshLayout.autoLoadmore();
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mId = getIntent().getIntExtra(EXTRA_SPECIAL_ID, 0);
        comeFrom = getIntent().getStringExtra(COME_FROM_NAME);
        title = getIntent().getStringExtra(EXTRA_TITLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homeRecommendHeader.stopTimer();
    }

    @Override
    public int getId() {
        return mId;
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        homeRecommendHeader = new HomeRecommendHeader(this, new HomeRecommendHeader.OrderByChangeListener() {
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
//        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        mRecyclerView.addItemDecoration(new GridItemDecoration(getContext(), R.color.transparent,
//                R.dimen.product_grid_divider_h, R.dimen.product_grid_divider_h));
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
        if (mTitleBar != null) {
//            mTitleBar.setTitleTxt(title);
        }
    }

    @Override
    public void stopRefresh() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishRefresh();
        }
    }

    @Override
    public void stopLoadMore() {
//        if (mRecyclerView != null) {
//            mRecyclerView.stopLoadMore();
//        }
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
//        if (mRecyclerView != null) {
//            mRecyclerView.setLoadable(loadable);
//        }
    }

    @Override
    public String getComeFrom() {
        return comeFrom;
    }

    @Override
    public void setBannerData(List<String> banners) {
        if (banners == null) return;
        bannerView.setVisibility(View.VISIBLE);
        bannerView
                .setDelayTime(2000)
                .setImages(banners)
                .start();
    }


    @Override
    protected void onStart() {
        super.onStart();
        bannerView.setBannerAnimation(Transformer.Default);
        bannerView.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bannerView.stopAutoPlay();
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
