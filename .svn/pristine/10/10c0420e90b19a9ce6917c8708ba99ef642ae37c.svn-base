package com.a1magway.bgg.p.friend;

import android.support.annotation.NonNull;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.data.entity.InviteFriendData;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.refactor.BasePresenter;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.dialog.GeneralImageTextDialog;
import com.a1magway.bgg.v.friend.InviteFriendContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by enid on 2018/8/15.
 */

public class InviteFriendP extends BasePresenter<InviteFriendContract.View> implements InviteFriendContract.Presenter,
        InviteFriendAdapter.ClickInviteListener {

    @Inject
    APIManager apiManager;
    private InviteFriendAdapter inviteFriendAdapter;

    @Inject
    public InviteFriendP(@NonNull InviteFriendContract.View view) {
        super(view);
    }


    @Override
    public void inviteAllFriendUpgrade() {
//        GeneralImageTextDialog.show(mView.getActivity(), R.drawable.ic_order_details_reminder,R.string.dialog_invite_friend_limiting_text);
//        GeneralImageTextDialog.show(mView.getActivity(), R.drawable.ic_warning_gray,R.string.dialog_identity_card_error);
//        GeneralImageTextDialog.show(mView.getActivity(), R.drawable.ic_globe,R.string.dialog_add_identity_card);
        String user_id = String.valueOf(GlobalData.getInstance().getUserId());//"2416"
        StringBuilder builder = new StringBuilder();
        List<InviteFriendData> inviteFriendDataList = inviteFriendAdapter.getData();
        for (InviteFriendData inviteFriendData : inviteFriendDataList) {
            builder.append(inviteFriendData.getId())
                    .append(",");
        }
        String ids = builder.toString().substring(0, builder.length() - 1);
        inviteMyFriendUpgrade(user_id, ids);
        //全部邀请，改变按钮状态
        mView.setInviteAllFriendBtnNoSelected();
        inviteFriendAdapter.setAllAlreadyInvited();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getData();
    }

    @Override
    public void getData() {
        String user_id = String.valueOf(GlobalData.getInstance().getUserId());
        apiManager.getMyFriends(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<List<InviteFriendData>>bindToLifecycle())
                .subscribe(new Consumer<List<InviteFriendData>>() {
                    @Override
                    public void accept(List<InviteFriendData> inviteFriendData) throws Exception {
                        inviteFriendAdapter = new InviteFriendAdapter(getContext(), inviteFriendData);
                        inviteFriendAdapter.addItemClickListener(InviteFriendP.this);
                        mView.setAdapter(inviteFriendAdapter);
                        if (inviteFriendAdapter.getCanInvitedCount() == 0) {
                            mView.setInviteAllFriendBtnNoSelected();
                        }
                        if (inviteFriendData.size() == 0) {
                            mView.showEmptyLayout(true);
                        } else {
                            mView.showInviteAllFriendBtn();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showEmptyLayout(true);
                    }
                });

    }


    @Override
    public void clickInvite(int position) {
        if (!inviteFriendAdapter.getData().get(position).isCanInvited()) {
            GeneralImageTextDialog.show(mView.getActivity(), R.drawable.ic_order_details_reminder, R.string.dialog_invite_friend_limiting_text);
        } else {
            inviteFriendAdapter.getData().get(position).setCanInvited(false);
            inviteFriendAdapter.notifyItemChanged(position);
            String user_id = String.valueOf(GlobalData.getInstance().getUserId());
            String ids = String.valueOf(inviteFriendAdapter.getData().get(position).getId());
            inviteMyFriendUpgrade(user_id, ids);
            if (inviteFriendAdapter.getCanInvitedCount() == 0) {
                mView.setInviteAllFriendBtnNoSelected();
            }
        }
    }


    /**
     * 邀请好友升级
     */
    private void inviteMyFriendUpgrade(String user_id, String ids) {
        apiManager.inviteMyFriendUpgrade(user_id, ids)
                .compose(this.<String>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<String>(getContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(String s) {
                        super.onNext(s);
                    }
                });
    }

}
