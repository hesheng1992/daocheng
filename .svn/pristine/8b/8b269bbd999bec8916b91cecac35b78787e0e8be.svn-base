package com.a1magway.bgg.v.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.data.entity.AccountBankCardData;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.account.DaggerAccountManageComponent;
import com.a1magway.bgg.di.module.account.AccountManageModule;
import com.a1magway.bgg.p.account.AccountManageP;
import com.a1magway.bgg.refactor.AppRefreshLayout;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.util.code.RequestCode;
import com.a1magway.bgg.widget.TitleBar;
import com.a1magway.bgg.widget.divider.LinearItemDecoration;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

/**
 * 账户管理
 */
public class AccountManageActivity extends PActivity<AccountManageP> implements AccountManageContract.View {

    @BindView(R.id.account_manage_titleBar)
    TitleBar mTitleBar;

    @BindView(R.id.account_manage_layout_refresh)
    AppRefreshLayout mRefreshLayout;

    @BindView(R.id.account_manage_recycler)
    RecyclerView mRecycleView;

    private static final String CARD_LIST_EXTRA_NAME = "card_list_type";

    public static final String CARD_LIST_TYPE_MANAGE = "card_list_type_manage";

    public static final String CARD_LIST_TYPE_SELECT = "card_list_type_select";

    private String mType = "";

    public static final void start(Context context,String type) {
        Intent intent = new Intent(context, AccountManageActivity.class);
        intent.putExtra(CARD_LIST_EXTRA_NAME,type);
        JumpUtil.startActivity(context, intent);
    }

    public static final void startForResult(Context context,String type,int requestCode) {
        Intent intent = new Intent(context, AccountManageActivity.class);
        intent.putExtra(CARD_LIST_EXTRA_NAME,type);
        JumpUtil.startActivityForResult(context, intent,requestCode);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_account_manage;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerAccountManageComponent.builder()
                .appComponent(appComponent)
                .accountManageModule(new AccountManageModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadData(false);
            }
        });

        mTitleBar.setDefLeftImgClickListener(this);
        mTitleBar.setShowMore(R.drawable.ic_add_black);
        mTitleBar.setMoreClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAccountActivity.startForResult(AccountManageActivity.this);
            }
        });
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mType = getIntent().getStringExtra(CARD_LIST_EXTRA_NAME);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCode.REQUEST_CODE_ADD_BANKCARD_SUCCESS && resultCode == RESULT_OK){
            mPresenter.loadData();
        }
    }

    @Subscribe
    public void getEventBus(AccountBankCardData data) {
        if (data!=null) {
            mPresenter.selectBankResult(data);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleView.addItemDecoration(new LinearItemDecoration(this,R.color.transparent,R.dimen.account_bank_card_divider));
        mRecycleView.setAdapter(adapter);
    }

    @Override
    public void stopRefresh() {
        if (mRefreshLayout != null){
            mRefreshLayout.finishRefresh();
        }
    }

    @Override
    public void setLoadable(boolean loadable) {

    }

    @Override
    public void stopLoadMore() {

    }

    @Override
    public String getStartType() {
        return mType;
    }

}
