package com.a1magway.bgg.v.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PFragment;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerCartComponent;
import com.a1magway.bgg.di.module.CartModule;
import com.a1magway.bgg.p.cart.CartP;
import com.a1magway.bgg.refactor.AppRefreshLayout;
import com.a1magway.bgg.util.StringFormatUtil;
import com.a1magway.bgg.v.main.MainActivity;
import com.a1magway.bgg.widget.divider.LinearItemDecoration;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

/** 购物车 Created by jph on 2017/7/20. */
public class CartFragment extends PFragment<CartP> implements ICartV {

    private static final String EXTRA_IS_SHOW_TITLE = "extra_is_show_title";

    @BindView(R.id.cart_txt_title)
    TextView mTitleTxt;

    @BindView(R.id.cart_layout_refresh)
    AppRefreshLayout refreshLayout;

    @BindView(R.id.cart_recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.cart_layout_bottom)
    ViewGroup mBottomLayout;

    @BindView(R.id.cart_txt_total_count)
    TextView mTotalCountTxt;

    @BindView(R.id.cart_txt_total_price)
    TextView mTotalPriceTxt;

    public static CartFragment newInstance(boolean isShowTitle) {

        Bundle args = new Bundle();
        args.putBoolean(EXTRA_IS_SHOW_TITLE, isShowTitle);

        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);

        DaggerCartComponent.builder()
                .appComponent(appComponent)
                .cartModule(new CartModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.cart_fragment_cart;
    }

    @Override
    public void onCreateV(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.onCreateV(savedInstanceState, contentView);
        refreshLayout.setOnRefreshLoadmoreListener(
                new OnRefreshLoadmoreListener() {
                    @Override
                    public void onLoadmore(RefreshLayout refreshlayout) {
                        mPresenter.loadMore();
                    }

                    @Override
                    public void onRefresh(RefreshLayout refreshlayout) {
                        mPresenter.reload();
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.reload();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);

        // 是否显示title
        boolean isShowTitle = getArguments().getBoolean(EXTRA_IS_SHOW_TITLE);
        if (isShowTitle) {
            MainActivity.setNullStatusBarHeight(contentView);
        }

        mTitleTxt.setVisibility(isShowTitle ? View.VISIBLE : View.GONE);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(
                new LinearItemDecoration(getContext(), R.color.transparent, R.dimen.cart_divider));
    }

    @OnClick(R.id.cart_txt_order)
    public void onClickOrder(View v) {
        // 点击结算
        mPresenter.clickOrder();
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
    public void stopLoadMore() {
        refreshLayout.finishLoadmore();
    }

    @Override
    public void setLoadEnable() {
        refreshLayout.setLoadmoreFinished(true);
    }

    @Override
    public void showTotal(int count, double totalPrice) {
        if (count == 0) {
            mBottomLayout.setVisibility(View.GONE);
            return;
        }

        mBottomLayout.setVisibility(View.VISIBLE);
        mTotalCountTxt.setText(String.format(getString(R.string.cart_format_total_count), count));
        mTotalPriceTxt.setText(StringFormatUtil.getPrice(getContext(), totalPrice));
    }


}
