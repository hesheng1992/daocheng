package com.a1magway.bgg.v.account;

import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.data.entity.AccountDataBean;
import com.a1magway.bgg.refactor.BaseContract;

/**
 * Created by enid on 2018/6/6.
 */

public interface MyWalletContract {
    interface View extends BaseContract.BaseView{
        public void setAccountData(AccountDataBean data);
        void setRecyclerViewAdapter(RecyclerView.Adapter adapter);
        void setLoadable(boolean loadable);
        void stopLoadMore();
        void setRecyclerView(boolean gone);
    }

    interface Presenter extends BaseContract.BasePresenter {
        public void getAccountData();
    }
}
