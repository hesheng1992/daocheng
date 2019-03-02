package com.a1magway.bgg.p.account;

import android.support.annotation.NonNull;
import android.view.View;

import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.AccountDetailData;
import com.a1magway.bgg.data.entity.AccountWalletData;
import com.a1magway.bgg.data.repository.IAccountData;
import com.a1magway.bgg.refactor.BasePresenter;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.v.account.AccountDetailAdapter;
import com.a1magway.bgg.v.account.MyWalletContract;
import com.a1magway.bgg.v.order.OrderDetailsActivity;
import com.almagway.common.utils.CollectionUtil;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by enid on 2018/6/13.
 */

public class MyWalletP extends BasePresenter<MyWalletContract.View> implements MyWalletContract.Presenter {
    private IAccountData mIAccountData;

    private int mLastId = 0;

    @Inject
    AccountDetailAdapter mAccountDetailAdapter;

    @Inject
    public MyWalletP(@NonNull MyWalletContract.View view, IAccountData iAccountData) {
        super(view);
        this.mIAccountData = iAccountData;
    }

    @Override
    public void getAccountData() {
        mIAccountData.getUserAccount(GlobalData.getInstance().getUserId(), mLastId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<AccountWalletData>(getContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.stopLoadMore();
                    }

                    @Override
                    public void onNext(AccountWalletData accountWalletData) {
                        if (mLastId == 0 && (accountWalletData.getUserGeneral() == null ||
                                accountWalletData.getUserGeneral().size() == 0)) {
                            mView.setRecyclerView(true);
                        } else {
                            mView.setRecyclerView(false);
                        }
                        if (accountWalletData.getUserAccount() != null) {
                            mView.setAccountData(accountWalletData.getUserAccount());
                        }

                        if (mLastId == 0) {
                            mAccountDetailAdapter.setList(accountWalletData.getUserGeneral());
                        } else {
                            mAccountDetailAdapter.addList(accountWalletData.getUserGeneral());
                        }

                        mView.setLoadable(CollectionUtil.isNotEmpty(accountWalletData.getUserGeneral()));
                    }

                    @Override
                    public void onComplete() {
                        mView.stopLoadMore();
                    }
                });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mView.setRecyclerViewAdapter(mAccountDetailAdapter);
        mAccountDetailAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //跳转到订单详情页面
                AccountDetailData accountDetailData = mAccountDetailAdapter.getItem(position);
                String orderNum = accountDetailData.getOrderNum();
                String creatorId = accountDetailData.getCreatorId();
                if (orderNum != null) {
                    if (creatorId != null) {
                        OrderDetailsActivity.start(getContext(), orderNum,
                                Integer.valueOf(creatorId),false);
                    } else {
                        OrderDetailsActivity.start(getContext(), orderNum);
                    }
                }

            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        });
    }

    public void loadMore() {
        if (mAccountDetailAdapter.getRealItemCount() == 0) {
            mLastId = 0;
        } else {
            mLastId = mAccountDetailAdapter.getItem(mAccountDetailAdapter.getRealItemCount() - 1).getId();
        }
        getAccountData();
    }
}
