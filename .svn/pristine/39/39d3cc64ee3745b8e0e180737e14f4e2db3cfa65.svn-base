package com.a1magway.bgg.v.seckill;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PFragmentInPager;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerSecKillComponent;
import com.a1magway.bgg.di.module.SecKillModule;
import com.a1magway.bgg.p.seckill.SecKillP;
import com.a1magway.bgg.widget.LoadMoreRecyclerView;
import com.a1magway.bgg.widget.OldRefreshLayout;
import com.a1magway.bgg.widget.divider.GridItemDecoration;
import com.xxxrecylcerview.XXXRecyclerView;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 秒杀专区子界面，显示秒杀列表
 * Created by jph on 2017/7/27.
 */
public class SecKillFragment extends PFragmentInPager<SecKillP> implements ISecKillV {
    private static final String EXTRA_SECKILL_TYPE = "extra_seckill_type";

    private int mSecKillType;

    @BindView(R.id.seckill_layout_refresh)
    OldRefreshLayout mOldRefreshLayout;
    @BindView(R.id.seckill_recycler)
    LoadMoreRecyclerView mRecyclerView;

    public static SecKillFragment newInstance(int secKillType) {

        Bundle args = new Bundle();

        SecKillFragment fragment = new SecKillFragment();
        args.putInt(EXTRA_SECKILL_TYPE, secKillType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);

        DaggerSecKillComponent.builder()
                .appComponent(appComponent)
                .secKillModule(new SecKillModule(this, mSecKillType))
                .build()
                .inject(this);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.seckill_fragment_seckill;
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        mSecKillType = getArguments().getInt(EXTRA_SECKILL_TYPE);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.addItemDecoration(new GridItemDecoration(getContext(), R.color.transparent,
                R.dimen.seckill_divider_h, R.dimen.seckill_divider_v));

        mOldRefreshLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPresenter.reload();
            }
        });

        mRecyclerView.setOnLoadMoreListener(new XXXRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadMore();
            }
        });
    }

    @Override
    public void setRecyclerViewAdapter(BaseRecyclerAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void stopRefresh() {
        mOldRefreshLayout.refreshComplete();
    }

    @Override
    public void stopLoadMore() {
        mRecyclerView.stopLoadMore();
    }

    @Override
    public void setLoadable(boolean loadable) {
        mRecyclerView.setLoadable(loadable);
    }


}
