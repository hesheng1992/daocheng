package com.a1magway.bgg.v.cate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PFragment;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerCateComponent;
import com.a1magway.bgg.di.module.CateModule;
import com.a1magway.bgg.p.cate.CateP;
import com.a1magway.bgg.refactor.AppRefreshLayout;
import com.a1magway.bgg.v.main.MainActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * 分类界面
 * Created by jph on 2017/7/20.
 */
public class CateFragment extends PFragment<CateP> implements ICateV {

    @BindView(R.id.cate_layout_refresh)
    AppRefreshLayout mRefreshLayout;
    @BindView(R.id.cate_recycler)
    RecyclerView mRecyclerView;

    @Override
    public int getContentViewLayoutId() {
        return R.layout.cate_fragment_cate;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);

        DaggerCateComponent.builder()
                .appComponent(appComponent)
                .cateModule(new CateModule(this))
                .build()
                .inject(this);

    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);

        MainActivity.setNullStatusBarHeight(contentView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.reload();
            }
        });
    }

    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void stopRefresh() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishRefresh();
        }
    }
}
