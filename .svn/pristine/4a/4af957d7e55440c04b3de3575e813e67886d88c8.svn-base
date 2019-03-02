package com.a1magway.bgg.v.personal;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.InvitationCodeData;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerInvitationCodeComponent;
import com.a1magway.bgg.di.module.InvitationCodeModule;
import com.a1magway.bgg.p.personal.InvitationCodeP;
import com.a1magway.bgg.refactor.PresenterActivity;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.util.JumpUtil;
import com.almagway.common.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class InvitationCodeActivity extends PresenterActivity<InvitationCodeP> implements IInvitationCodeV {

    @BindView(R.id.invitation_code_imag)
    ImageView mInvitationCodeImage;

    @BindView(R.id.invitation_code_text)
    TextView mInvitationCodeText;
    private ClipboardManager mClipboardManager;

    @OnClick({R.id.invitation_code_refresh_btn, R.id.invitation_code_copy_btn})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.invitation_code_refresh_btn:
                presenter.refreshQrcode();
                break;
            case R.id.invitation_code_copy_btn:
                ClipData mClipData = ClipData.newPlainText("Label", mInvitationCodeText.getText());
                mClipboardManager.setPrimaryClip(mClipData);
                break;
        }
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerInvitationCodeComponent.builder()
                .appComponent(appComponent)
                .invitationCodeModule(new InvitationCodeModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_invitation_code;
    }

    @Override
    public Context getContext() {
        return this;
    }

    public static final void start(Context context) {
        Intent intent = new Intent(context, InvitationCodeActivity.class);
        JumpUtil.startActivity(context, intent);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTextTitle("邀请码");
        mInvitationCodeText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        setImageMore(true, R.drawable.ic_share_plane);
        setMoreListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               presenter.shareInvitationCode(getSupportFragmentManager());
            }
        });

        mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        mClipboardManager.addPrimaryClipChangedListener(mClipChangedListener);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        presenter.getInvitationCode();
    }

    ClipboardManager.OnPrimaryClipChangedListener mClipChangedListener = new ClipboardManager.OnPrimaryClipChangedListener() {
        @Override
        public void onPrimaryClipChanged() {

            if (mClipboardManager.hasPrimaryClip() && mClipboardManager.getPrimaryClip().getItemCount() > 0) {

                CharSequence addedText = mClipboardManager.getPrimaryClip().getItemAt(0).getText();

                if (addedText != null) {
                    ToastUtil.showShort("复制成功");
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mClipboardManager.removePrimaryClipChangedListener(mClipChangedListener);
    }

    @Override
    public void setInvitationData(InvitationCodeData data) {
        ImageLoaderUtil.displayImage(mInvitationCodeImage,data.getUrl());
        mInvitationCodeText.setText(data.getInviteCode());
    }

}
