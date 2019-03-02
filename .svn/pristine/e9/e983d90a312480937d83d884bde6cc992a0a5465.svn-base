package com.a1magway.bgg.v.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PFragmentInPager;
import com.a1magway.bgg.data.entity.OrderType;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.order.DaggerOrderListComponent;
import com.a1magway.bgg.di.module.order.OrderListModule;
import com.a1magway.bgg.p.order.OrderListP;
import com.a1magway.bgg.refactor.AppRefreshLayout;
import com.a1magway.bgg.util.pay.PayCallback;
import com.a1magway.bgg.widget.LoadMoreRecyclerView;
import com.a1magway.bgg.widget.dialog.PayDialogFragment;
import com.a1magway.bgg.widget.divider.LinearItemDecoration;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xxxrecylcerview.XXXRecyclerView;

import butterknife.BindView;

/**
 * 显示订单列表的界面
 * Created by jph on 2017/8/17.
 */
public class OrderListFragment extends PFragmentInPager<OrderListP> implements IOrderListV {

    private static final String EXTRA_ORDER_TYPE = "extra_order_type";

    @BindView(R.id.order_list_layout_refresh)
    AppRefreshLayout mRefreshLayout;
    @BindView(R.id.order_list_recycler)
    LoadMoreRecyclerView mRecyclerView;

    public static OrderListFragment newInstance(@OrderType int type) {

        Bundle args = new Bundle();
        args.putInt(EXTRA_ORDER_TYPE, type);

        OrderListFragment fragment = new OrderListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerOrderListComponent.builder()
                .appComponent(appComponent)
                .orderListModule(new OrderListModule(this, getArguments().getInt(EXTRA_ORDER_TYPE)))
                .build()
                .inject(this);
    }

    //切换viewPager刷新当前Fragment数据
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mPresenter!=null){
            mPresenter.clickCurrentTabRefreshData();
        }
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.order_fragment_list;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.reload();
            }
        });
        mRefreshLayout.setEnableLoadmore(false);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new LinearItemDecoration(getContext(), R.color.transparent,
                R.dimen.order_list_divider_10));
        mRecyclerView.setOnLoadMoreListener(new XXXRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadMore();
            }
        });

        //添加顶部占位
        View vHeader = new View(getContext());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getResources().getDimensionPixelOffset(R.dimen.order_list_header_divider));
        vHeader.setLayoutParams(lp);
        mRecyclerView.addHeaderView(vHeader);
    }

    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void stopRefresh() {
        mRefreshLayout.finishRefresh();
    }

    @Override
    public void stopLoadMore() {
        mRecyclerView.stopLoadMore();
    }

    @Override
    public void setLoadable(boolean loadable) {
        mRecyclerView.setLoadable(loadable);
    }

    @Override
    public void showPayDialog(final String orderNum) {
        final PayDialogFragment dialogFragment = PayDialogFragment.newInstance(orderNum);
        dialogFragment.setPayCallback(new PayCallback() {
            @Override
            public void onSuccess(int type) {
                dialogFragment.dismiss();
                mRefreshLayout.autoRefresh();
                OrderDetailsActivity.start(getContext(), orderNum);
            }

            @Override
            public void onFailed(String msg) {
                dialogFragment.dismiss();
            }
        });
        dialogFragment.show(getChildFragmentManager(), "PayDialog");
    }

    @Override
    public FragmentManager getFragmentMg() {
        return getFragmentManager();
    }


}
