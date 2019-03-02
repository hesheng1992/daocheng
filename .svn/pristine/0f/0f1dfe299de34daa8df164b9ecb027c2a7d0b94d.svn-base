package com.a1magway.bgg.v.friend;

import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.refactor.BaseContract;

/**
 * Created by enid on 2018/8/15.
 */

public interface InviteFriendContract {
    interface View extends BaseContract.BaseView {
        void setAdapter(RecyclerView.Adapter adapter);

        void showEmptyLayout(boolean show);

        void showInviteAllFriendBtn();

        void setInviteAllFriendBtnNoSelected();
    }


    interface Presenter extends BaseContract.BasePresenter {
        /**
         * 邀请全部好友升级
         */
        void inviteAllFriendUpgrade();

        void getData();
    }
}
