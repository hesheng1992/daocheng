package com.a1magway.bgg.v.member;

import android.support.v7.widget.RecyclerView;
import com.a1magway.bgg.refactor.BaseContract;

/**
 * author: Beaven
 * date: 2017/10/31 15:04
 */

public interface MemberCardsContract {

    interface View extends BaseContract.BaseView {

        void setAdapter(RecyclerView.Adapter adapter);
    }
}
