package com.a1magway.bgg.v.invitation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerInvitationComponent;
import com.a1magway.bgg.di.module.InvitationModule;
import com.a1magway.bgg.p.register.InvitationP;
import com.a1magway.bgg.refactor.PresenterActivity;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.v.qrscan.MyCaptureActivity;
import com.a1magway.bgg.widget.ClearEditText;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

public class InvitationActivity extends PresenterActivity<InvitationP> implements InvitationContract.View {

    public static final String TO_UPDATE_USER = "toUpdateUser";//来自绑定邀请码应道页面

    @BindView(R.id.invitation_code_edt)
    ClearEditText mInvitationCodeEdt;

    @BindView(R.id.invitation_scan_code_tv)
    TextView mInvitationScanTv;

    @BindView(R.id.invitation_commit_btn)
    Button mInvitationCommitBtn;

    private TextChangeListener mTextChangeListener = new TextChangeListener(this);

    private boolean toUpdateUser;//绑定邀请码后去升级账号


    @OnClick({R.id.invitation_scan_code_tv, R.id.invitation_commit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.invitation_scan_code_tv:
                zxingScan();
                break;
            case R.id.invitation_commit_btn:
                presenter.bindInvitationCode();
                break;
            default:
                break;
        }
    }

    public static final void start(Context context) {
        Intent intent = new Intent(context, InvitationActivity.class);
        JumpUtil.startActivity(context, intent);
    }


    public static final void startComeInvitationGuideActivity(Context context) {
        Intent intent = new Intent(context, InvitationActivity.class);
        intent.putExtra(TO_UPDATE_USER,true);
        JumpUtil.startActivity(context, intent);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_invitation;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerInvitationComponent.builder()
                .appComponent(appComponent)
                .invitationModule(new InvitationModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setImageBack(true, R.drawable.ic_title_close);
        setTextTitle(R.string.invitation_title);

        toUpdateUser = getIntent().getBooleanExtra(TO_UPDATE_USER,false);
        presenter.toUpdateUser(toUpdateUser);

        mInvitationCodeEdt.addTextChangedListener(mTextChangeListener);
    }

    @Override
    public String getInvitationCode() {
        return mInvitationCodeEdt.getEditable().toString().trim();
    }

    @Override
    public void showLoading(@Nullable String text) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    private static class TextChangeListener implements ClearEditText.MyTextWatcher {

        private WeakReference<InvitationActivity> weakReference;

        TextChangeListener(InvitationActivity invitationActivity) {
            weakReference = new WeakReference<>(invitationActivity);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            InvitationActivity invitationActivity = weakReference.get();
            if (invitationActivity == null) return;
            if (invitationActivity.mInvitationCodeEdt.getEditable().length()
                    >= invitationActivity.getResources().getInteger(R.integer.invitation_code_length)) {
                if (!invitationActivity.mInvitationCommitBtn.isEnabled()) {
                    invitationActivity.mInvitationCommitBtn.setEnabled(true);
                }
            } else {
                if (invitationActivity.mInvitationCommitBtn.isEnabled()) {
                    invitationActivity.mInvitationCommitBtn.setEnabled(false);
                }
            }
        }
    }

    private void zxingScan() {
        Intent intent = new Intent(this, MyCaptureActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == MyCaptureActivity.RESULT_CODE_SCAN_SUCCESS) {
                String scanResult = data.getStringExtra(MyCaptureActivity.EXTRA_RESULT_SUCCESS_STRING);
                mInvitationCodeEdt.setText(scanResult);
                //扫描二维码成功后无需提交直接绑定
                presenter.bindInvitationCode();
            }
        }
    }
}
