package com.a1magway.bgg.v.invitation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.p.register.InvitationP;
import com.a1magway.bgg.refactor.PresenterActivity;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.widget.ClearEditText;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class RegisterActivity extends PresenterActivity<InvitationP> implements InvitationContract.View{

    @BindView(R.id.register_phone_edt)
    ClearEditText registerPhoneEdt;
    @BindView(R.id.register_phone_code_edt)
    ClearEditText registerPhoneCodeEdt;
    @BindView(R.id.register_send_code_send_tv)
    TextView registerSendCodeSendTv;
    @BindView(R.id.send_code_code_layout)
    LinearLayout sendCodeCodeLayout;
    @BindView(R.id.register_set_pwd_edt)
    ClearEditText registerSetPwdEdt;
    @BindView(R.id.register_set_pwd_checkBox)
    CheckBox registerSetPwdCheckBox;
    @BindView(R.id.register_invitation_code_edt)
    ClearEditText registerInvitationCodeEdt;
    @BindView(R.id.register_scan_invitation_code_tv)
    TextView registerScanInvitationCodeTv;
    @BindView(R.id.register_agreement_checkBox)
    CheckBox registerAgreementCheckBox;
    @BindView(R.id.register_agreement_tv)
    TextView registerAgreementTv;
    @BindView(R.id.register_agreement_layout)
    LinearLayout registerAgreementLayout;
    @BindView(R.id.register_commit_btn)
    Button registerCommitBtn;
    @BindView(R.id.layout_root)
    LinearLayout layoutRoot;

    private TextChangeListener mTextChangeListener = new TextChangeListener(this);

    @OnCheckedChanged(R.id.register_set_pwd_checkBox)
    void onCheckedChanged() {
        if (registerSetPwdCheckBox.isChecked()) {
            // 如果选中，显示密码
            registerSetPwdEdt
                    .setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            registerSetPwdEdt.setSelection(registerSetPwdEdt.getEditable().length());
        } else {
            // 否则隐藏密码
            registerSetPwdEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
            registerSetPwdEdt.setSelection(registerSetPwdEdt.getEditable().length());
        }
    }

    @OnClick({R.id.register_send_code_send_tv, R.id.register_scan_invitation_code_tv, R.id.register_commit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_send_code_send_tv:
                break;
            case R.id.register_scan_invitation_code_tv:
                break;
            case R.id.register_commit_btn:
                break;
        }
    }

    public static final void start(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        JumpUtil.startActivity(context, intent);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
//        DaggerInvitationComponent.builder()
//                .appComponent(appComponent)
//                .invitationModule(new InvitationModule(this))
//                .build()
//                .inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
//        setImageBack(true, R.drawable.ic_title_close);
//        setTextTitle(R.string.invitation_title);
//        registerAgreementTv.setText(Html.fromHtml(getResources().getString(
//                R.string.send_verification_registration_agreement)));
//
//        registerPhoneEdt.addTextChangedListener(mTextChangeListener);
//        registerPhoneCodeEdt.addTextChangedListener(mTextChangeListener);
//        registerSetPwdEdt.addTextChangedListener(mTextChangeListener);
//        registerInvitationCodeEdt.addTextChangedListener(mTextChangeListener);
    }

    @Override
    public String getInvitationCode() {
        return registerInvitationCodeEdt.getEditable().toString().trim();
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

        private WeakReference<RegisterActivity> weakReference;

        TextChangeListener(RegisterActivity registerActivity) {
            weakReference = new WeakReference<>(registerActivity);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            RegisterActivity registerActivity = weakReference.get();
            if (registerActivity == null) return;
            if (registerActivity.registerPhoneEdt.getEditable().length()
                    >= registerActivity.getResources().getInteger(R.integer.phoneLength)
                    && registerActivity.registerPhoneCodeEdt.getEditable().length()
                    >= registerActivity.getResources().getInteger(R.integer.verification_code_length)
                    && registerActivity.registerSetPwdEdt.getEditable().length()
                    >= registerActivity.getResources().getInteger(R.integer.pwd_min_length)
                    && registerActivity.registerInvitationCodeEdt.getEditable().length()
                    >= registerActivity.getResources().getInteger(R.integer.invitation_code_length)) {
                if (!registerActivity.registerCommitBtn.isEnabled()) {
                    registerActivity.registerCommitBtn.setEnabled(true);
                }
            } else {
                if (registerActivity.registerCommitBtn.isEnabled()) {
                    registerActivity.registerCommitBtn.setEnabled(false);
                }
            }
        }
    }
}
