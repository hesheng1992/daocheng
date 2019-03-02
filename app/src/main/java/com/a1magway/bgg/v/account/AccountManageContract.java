package com.a1magway.bgg.v.account;

import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.common.ILoadingV;
import com.a1magway.bgg.data.entity.AccountBankCardData;
import com.a1magway.bgg.refactor.BaseContract;

/**
 * Created by enid on 2018/6/6.
 */

public interface AccountManageContract {
    interface View extends ILoadingV {
        void setRecyclerViewAdapter(RecyclerView.Adapter adapter);

        void stopRefresh();

        void setLoadable(boolean loadable);

        void stopLoadMore();

        String getStartType();
    }

    interface Presenter extends BaseContract.BasePresenter {

//        void bindInvitationCode();
        void  selectBankResult(AccountBankCardData accountBankCardData);

    }
}
