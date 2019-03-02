package com.a1magway.bgg.v.collection;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerCollectionComponent;
import com.a1magway.bgg.di.module.ProductCollectionModule;
import com.a1magway.bgg.p.collection.CollectionP;
import com.a1magway.bgg.refactor.AppRefreshLayout;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.widget.TitleBar;
import com.almagway.common.utils.ScreenUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xxxrecylcerview.XXXRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;

public class CollectionActivity extends PActivity<CollectionP> implements CollectionContract.View {

    @BindView(R.id.collection_recycler)
    XXXRecyclerView mRecyclerView;

    @BindView(R.id.collection_layout_refresh)
    AppRefreshLayout mRefreshLayout;

    @BindView(R.id.collection_title_bar)
    TitleBar mTitleBar;

    @BindView(R.id.collection_bottom_layout)
    LinearLayout llBottomLayout;

    @OnClick({R.id.collection_bottom_cancel_tv, R.id.collection_bottom_delete_tv})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.collection_bottom_cancel_tv:
                mPresenter.clickCancel();
                break;
            case R.id.collection_bottom_delete_tv:
                mPresenter.clickDelete();
                break;
        }
    }


    public static final void start(Context context) {
        Intent intent = new Intent(context, CollectionActivity.class);
        JumpUtil.startActivity(context, intent);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_collecton;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.reload();
            }
        });
        mRecyclerView.setLoadable(false);
        mRecyclerView.setOnLoadMoreListener(new XXXRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadData(false);
            }
        });
        mTitleBar.setDefLeftImgClickListener(this);
        mTitleBar.setShowMore(R.drawable.ic_more_horiz_black);
        mTitleBar.setMoreClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.showRadioButton();
            }
        });
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerCollectionComponent.builder()
                .appComponent(appComponent)
                .productCollectionModule(new ProductCollectionModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        mRecyclerView.addItemDecoration(new GridItemDecoration(getContext(), R.color.transparent,
//                R.dimen.product_grid_divider_h, R.dimen.product_grid_divider_h));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int itemCount = parent.getLayoutManager().getItemCount();
                int currentItem = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
                if (currentItem % 2 == 0) {// 第一列
                    outRect.left = ScreenUtil.dp2px(8f);
                    outRect.right = ScreenUtil.dp2px( 16f);
                } else if (currentItem % 2 == 1) { //最后列
                    outRect.left = ScreenUtil.dp2px(16f);
                    outRect.right = ScreenUtil.dp2px(8f);
                }
                outRect.bottom = ScreenUtil.dp2px( 10f);//最后一列--
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void stopRefresh() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishRefresh();
        }
    }

    @Override
    public void stopLoadMore() {
        if (mRecyclerView != null) {
            mRecyclerView.stopLoadMore();
        }
    }

    @Override
    public void setLoadEnable() {

    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {

    }

    @Override
    public void setDeleteLayoutShow(boolean show) {
        if (show) {
            llBottomLayout.setVisibility(View.VISIBLE);
        } else {
            llBottomLayout.setVisibility(View.INVISIBLE);
        }
    }

}
