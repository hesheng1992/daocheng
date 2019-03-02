package com.a1magway.bgg.v.friend;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.friend.DaggerInviteFriendComponent;
import com.a1magway.bgg.di.module.friend.InviteFriendModule;
import com.a1magway.bgg.p.friend.InviteFriendP;
import com.a1magway.bgg.refactor.PresenterActivity;
import com.a1magway.bgg.util.dialog.GeneralImageTextDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 邀请好友升级页面
 * Created by enid on 2018/8/15.
 */

public class InviteFriendActivity extends PresenterActivity<InviteFriendP> implements InviteFriendContract.View {
    @BindView(R.id.invite_friend_recyclerView)
    RecyclerView inviteFriendRecyclerView;
    @BindView(R.id.invite_friend_empty)
    ImageView inviteFriendEmpty;
    @BindView(R.id.invite_friend_upgrade)
    Button inviteFriendUpgrade;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_invite_friend;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerInviteFriendComponent.builder()
                .appComponent(appComponent)
                .inviteFriendModule(new InviteFriendModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTextTitle("我的好友");
        inviteFriendUpgrade.setSelected(true);
    }


    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        inviteFriendRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        inviteFriendRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showEmptyLayout(boolean show) {
        inviteFriendEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showInviteAllFriendBtn() {
        inviteFriendUpgrade.setVisibility(View.VISIBLE);
    }

    @Override
    public void setInviteAllFriendBtnNoSelected() {
        inviteFriendUpgrade.setSelected(false);
    }


    @OnClick(R.id.invite_friend_upgrade)
    public void onViewClicked() {
        if (inviteFriendUpgrade.isSelected()) {
            presenter.inviteAllFriendUpgrade();
        } else {
            GeneralImageTextDialog.show(this, R.drawable.ic_order_details_reminder, R.string.dialog_invite_friend_limiting_text);
        }
    }


}
