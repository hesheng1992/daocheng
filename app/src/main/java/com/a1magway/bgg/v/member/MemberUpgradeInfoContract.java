package com.a1magway.bgg.v.member;

import android.support.v7.widget.RecyclerView;
import com.a1magway.bgg.data.entity.MemberUpgradeInfo;
import com.a1magway.bgg.refactor.BaseContract;

/**
 * author: Beaven
 * date: 2017/10/31 15:04
 */

public interface MemberUpgradeInfoContract {

    interface View extends BaseContract.BaseLoadView {

        void setRecyclerViewAdapter(RecyclerView.Adapter adapter);

        void setPriceNormal(MemberUpgradeInfo info);

        void setPriceSell(MemberUpgradeInfo info);
    }


    interface Presenter extends BaseContract.BasePresenter {

        MemberUpgradeInfo getMemberUpgradeInfo();
    }
}
