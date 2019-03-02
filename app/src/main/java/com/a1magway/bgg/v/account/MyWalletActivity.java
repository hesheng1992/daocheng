package com.a1magway.bgg.v.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.AccountDataBean;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.account.DaggerMyWalletComponent;
import com.a1magway.bgg.di.module.account.MyWalletModule;
import com.a1magway.bgg.p.account.MyWalletP;
import com.a1magway.bgg.refactor.PresenterActivity;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.widget.LoadMoreRecyclerView;
import com.a1magway.bgg.widget.divider.LinearItemDecoration;
import com.almagway.common.utils.ToastUtil;
import com.xxxrecylcerview.XXXRecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 我的钱包
 */
public class MyWalletActivity extends PresenterActivity<MyWalletP> implements MyWalletContract.View {

    @BindView(R.id.my_wallet_withdraw_deposit_tv)
    TextView mWithdrawDeposit;

    @BindView(R.id.my_wallet_dongjie_money)
    TextView my_wallet_dongjie_money;

    @BindView((R.id.my_wallet_recycler))
    LoadMoreRecyclerView mRecyclerView;

    @Inject
    APIManager apiManager;

    private static final int REQUEST_CODE_WITHDRAW = 100;

    @OnClick({R.id.my_wallet_account_manage_tv, R.id.my_wallet_account_withdraw_tv})
    public void onClickAccount(View view) {
        switch (view.getId()) {
            case R.id.my_wallet_account_manage_tv:
                AccountManageActivity.start(this, AccountManageActivity.CARD_LIST_TYPE_MANAGE);
                break;
            case R.id.my_wallet_account_withdraw_tv:
                LoginData data = GlobalData.getInstance().getLoginData();
                if (apiManager != null) {
                    apiManager.getUserInfo(String.valueOf(data.getId()))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .compose(this.<LoginData>bindToLifecycle())
                            .subscribe(new Consumer<LoginData>() {
                                @Override
                                public void accept(LoginData loginData) throws Exception {
                                    if (loginData.getMemberGrade() >= 4) {
                                        AccountWithdrawActivity.startForResult(MyWalletActivity.this, REQUEST_CODE_WITHDRAW);
                                    } else {
                                        ToastUtil.showLong("会员等级不够，无法提现");
                                    }
                                    GlobalData.getInstance().setLoginData(loginData);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {

                                }
                            });
                }
                break;
        }
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_my_wallet;
    }

    public static final void start(Context context) {
        Intent intent = new Intent(context, MyWalletActivity.class);
        JumpUtil.startActivity(context, intent);
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerMyWalletComponent.builder()
                .appComponent(appComponent)
                .myWalletModule(new MyWalletModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setImageBack(true, R.drawable.ic_arrow_left);
        setTextTitle("我的钱包");
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        if (presenter != null) {
            presenter.loadMore();
        } else {
            Log.d("MyWalletActivity", "presenter is null");
        }
    }


    @Override
    public void setAccountData(AccountDataBean data) {
        if (data.getWithdrawDeposit()==null){
            data.setWithdrawDeposit("0.00");
        }
        mWithdrawDeposit.setText(String.format(getString(R.string.format_price), data.getWithdrawDeposit()));
        my_wallet_dongjie_money.setText(String.format(getString(R.string.format_price), data.getFrozen()));
    }

    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new LinearItemDecoration(this, R.color.gray_c9, R.dimen.divider_1));
        mRecyclerView.setLoadable(true);
        mRecyclerView.setOnLoadMoreListener(new XXXRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadMore();
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setLoadable(boolean loadable) {
        if (mRecyclerView != null) {
            mRecyclerView.setLoadable(loadable);
        }
    }

    @Override
    public void stopLoadMore() {
        if (mRecyclerView != null) {
            mRecyclerView.stopLoadMore();
        }
    }

    @Override
    public void setRecyclerView(boolean gone) {
        if (gone){
            mRecyclerView.setVisibility(View.GONE);
        }else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_WITHDRAW && resultCode == RESULT_OK) {
            presenter.getAccountData();
        }
    }
}
