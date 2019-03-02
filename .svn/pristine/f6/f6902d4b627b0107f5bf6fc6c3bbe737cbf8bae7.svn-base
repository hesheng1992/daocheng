package com.a1magway.bgg.v.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import com.a1magway.bgg.R;
import com.a1magway.bgg.common.SoftKeyBroadManager;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerUserVerificationComponent;
import com.a1magway.bgg.di.module.UserVerificationModule;
import com.a1magway.bgg.p.login.UserVerificationP;
import com.a1magway.bgg.refactor.PresenterActivity;
import com.a1magway.bgg.util.code.RequestCode;
import com.a1magway.bgg.widget.ClearEditText;
import java.util.List;

/** 发送验证码 */
public class UserVerificationActivity extends PresenterActivity<UserVerificationP>
        implements UserVerificationContract.View {

    private static final String VERIFICATION_CODE_TYPE = "VerificationCodeType";

    private int verificationCodeType;

    @BindView(R.id.registration_agreement_tv)
    TextView mRegistrationAgreementTv;

    @BindViews({R.id.send_code_phone_edt, R.id.send_code_code_edt})
    List<ClearEditText> mEditTexts;

    @BindView(R.id.send_code_send_tv)
    TextView mSendTv;

    @BindView(R.id.registration_agreement_layout)
    LinearLayout mLinearLayout;

    @BindView(R.id.send_code_next_btn)
    Button mNextButton;

    @BindView(R.id.registration_agreement_checkBox)
    CheckBox mCheckBox;

    @BindView(R.id.layout_root)
    LinearLayout layoutRoot;

    private SoftKeyBroadManager manager;

    public static void startSendVerificationCodeActivity(Activity context, int type) {
        Intent intent = new Intent(context, UserVerificationActivity.class);
        intent.putExtra(VERIFICATION_CODE_TYPE, type);
        context.startActivityForResult(intent, RequestCode.REQUEST_CODE_SEND_CODE);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        verificationCodeType = getIntent().getIntExtra(VERIFICATION_CODE_TYPE, 0);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_send_code;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        verificationCodeType = getIntent().getIntExtra(VERIFICATION_CODE_TYPE, 0);
        mEditTexts.get(0).addTextChangedListener(mOnTextChangeListener);
        mEditTexts.get(1).addTextChangedListener(mOnTextChangeListener);
        if (verificationCodeType == UserVerificationP.CODE_TYPE_REGISTER) {
            setTextTitle(getResources().getString(R.string.common_title_register));
        } else {
            setTextTitle(getResources().getString(R.string.common_title_forget));
            mLinearLayout.setVisibility(View.INVISIBLE);
        }
        mRegistrationAgreementTv.setText(
                Html.fromHtml(
                        getResources()
                                .getString(R.string.send_verification_registration_agreement)));
        manager = new SoftKeyBroadManager(layoutRoot);
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerUserVerificationComponent.builder()
                .appComponent(appComponent)
                .userVerificationModule(new UserVerificationModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.addSoftKeyboardStateListener(softKeyboardStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.removeSoftKeyboardStateListener(softKeyboardStateListener);
    }

    @OnCheckedChanged(R.id.registration_agreement_checkBox)
    void onCheckChanged() {
        if (verificationCodeType == UserVerificationP.CODE_TYPE_FORGET) {
            return;
        }
        presenter.canGoNext();
    }

    @OnClick({
        R.id.send_code_next_btn,
        R.id.send_code_send_tv,
        R.id.registration_agreement_tv,
    })
    public void nextClick(View v) {
        switch (v.getId()) {
            case R.id.send_code_next_btn:
                presenter.onNextBtnClick(
                        verificationCodeType, mEditTexts.get(0).getEditable().toString());
                break;
            case R.id.send_code_send_tv:
                presenter.onCountDownTimerTvClick(verificationCodeType);
                break;
            case R.id.registration_agreement_tv:
                // TODO: 2017/8/4
                break;
        }
    }

    @Override
    public void showVerificationCodeTime(CharSequence time, boolean isEnable) {
        mSendTv.setEnabled(isEnable);
        mSendTv.setText(time);
    }

    @Override
    public String getPhoneString() {
        return mEditTexts.get(0).getEditable().toString().trim();
    }

    @Override
    public String getVerificationCodeString() {
        return mEditTexts.get(1).getEditable().toString().trim();
    }

    @Override
    public boolean getCheckBoxChecked() {
        return mCheckBox.isChecked();
    }

    @Override
    public void setBtnEnable(boolean enable) {
        presenter.setEnable(mNextButton, enable);
    }

    private OnTextChangeListener mOnTextChangeListener = new OnTextChangeListener();

    private class OnTextChangeListener implements ClearEditText.MyTextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (mEditTexts.get(0).getEditable().length()
                    >= getResources().getInteger(R.integer.phoneLength)) {
                if (mSendTv.getText()
                        .toString()
                        .equals(getResources().getString(R.string.send_verification_send))) {
                    presenter.setEnable(mSendTv, true);
                }
            } else {
                if (mSendTv.getText()
                        .toString()
                        .equals(getResources().getString(R.string.send_verification_send))) {
                    presenter.setEnable(mSendTv, false);
                }
            }
            if (verificationCodeType == UserVerificationP.CODE_TYPE_FORGET) {
                presenter.canGoNextForForget();
            } else {
                presenter.canGoNext();
            }
        }
    }

    private SoftKeyBroadManager.SoftKeyboardStateListener softKeyboardStateListener =
            new SoftKeyBroadManager.SoftKeyboardStateListener() {
                @Override
                public void onSoftKeyboardOpened(int keyboardHeightInPx) {
                    editLayout(mEditTexts.get(0), true);
                }

                @Override
                public void onSoftKeyboardClosed() {
                    editLayout(mEditTexts.get(0), false);
                }
            };

    private void editLayout(View view, boolean isOpen) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int topMargin = isOpen ? params.topMargin / 3 : params.topMargin * 3;
        int leftMargin = params.leftMargin;
        int bottomMargin = params.bottomMargin;
        int rightMargin = params.rightMargin;
        params.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        view.requestLayout();
    }
}
