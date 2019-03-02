package com.a1magway.bgg.v.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.AccountUserData;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.account.DaggerAddAccountComponent;
import com.a1magway.bgg.di.module.account.AddAccountModule;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.p.account.AddAccountP;
import com.a1magway.bgg.refactor.PresenterActivity;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.util.code.RequestCode;
import com.a1magway.bgg.v.web.WebActivity;
import com.a1magway.bgg.widget.ClearEditTextNew;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * 添加账户
 */
public class AddAccountActivity extends PresenterActivity<AddAccountP> implements AddAccountContract.View {

    @BindView(R.id.add_account_name_edt)
    ClearEditTextNew mNameEdt;

    @BindView(R.id.add_account_idNum_edt)
    ClearEditTextNew mIDNumberEdt;

//    @BindView(R.id.add_account_type_tv)
//    TextView mAccountTypeTv;

    @BindView(R.id.add_account_account_edt)
    ClearEditTextNew mAccountNumberEdt;

    @BindView(R.id.add_account_phone_edt)
    ClearEditTextNew mPhoneEdt;

    @BindView(R.id.add_account_verif_code_edt)
    ClearEditTextNew mVerifCodeEdt;

    @BindView(R.id.add_account_verif_code_send_tv)
    TextView mVerifCodeSendTv;

    @BindView(R.id.add_account_agreement_tv)
    TextView mAgreementTv;

    @BindView(R.id.add_account_commit_btn)
    Button mCommitBtn;

    @BindView(R.id.add_account_bank_logo)
    ImageView mBankLogoImage;

    @BindView(R.id.add_account_checkBox)
    CheckBox mCheckBox;

    private AddAccountActivity.TextChangeListener mTextChangeListener = new AddAccountActivity.TextChangeListener(this);

    private String mBankCardType = "";

    @OnClick({R.id.add_account_verif_code_send_tv, R.id.add_account_commit_btn, R.id.add_account_agreement_tv})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.add_account_verif_code_send_tv:
                presenter.sendVerification();
                break;
            case R.id.add_account_commit_btn:
                if (mCommitBtn.isClickable()) {
                    presenter.insertBankCard();
                }

                break;
            case R.id.add_account_agreement_tv:
                WebActivity.start(this, getString(R.string.personal_contact_buy_notice_url));
                break;
        }
    }

    @OnCheckedChanged(R.id.add_account_checkBox)
    void onCheckChanged() {
        checkCommitButtonIsEnable();
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_add_account;
    }

    public static final void start(Context context) {
        Intent intent = new Intent(context, AddAccountActivity.class);
        JumpUtil.startActivity(context, intent);
    }

    public static final void startForResult(Context context) {
        Intent intent = new Intent(context, AddAccountActivity.class);
        JumpUtil.startActivityForResult(context, intent, RequestCode.REQUEST_CODE_ADD_BANKCARD_SUCCESS);
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerAddAccountComponent.builder()
                .appComponent(appComponent)
                .addAccountModule(new AddAccountModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setImageBack(true, R.drawable.ic_arrow_left);
        setTextTitle("添加账户");
        mAgreementTv.setText(Html.fromHtml(getResources().getString(
                R.string.add_account_bound_bank_agreement)));
        mNameEdt.addTextChangedListener(mTextChangeListener);
        mIDNumberEdt.addTextChangedListener(mTextChangeListener);
        mAccountNumberEdt.addTextChangedListener(mTextChangeListener);
        mPhoneEdt.addTextChangedListener(mTextChangeListener);
        mVerifCodeEdt.addTextChangedListener(mTextChangeListener);
        //
        mBankLogoImage.setVisibility(View.GONE);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        presenter.userInfo();
    }

    @Override
    public void showVerificationCodeTime(CharSequence time, boolean isEnable) {
        if (mVerifCodeSendTv != null) {
            mVerifCodeSendTv.setEnabled(isEnable);
            mVerifCodeSendTv.setText(time);
        }
    }

    @Override
    public String getName() {
        return mNameEdt.getEditable().toString().trim();
    }

    @Override
    public String getIDCardNumber() {
        return mIDNumberEdt.getEditable().toString().trim();
    }

    @Override
    public void setAccountType(String accountType) {
        mBankCardType = accountType;
//        mAccountTypeTv.setText(accountType);
    }

    @Override
    public String getBankCardNumber() {
        return mAccountNumberEdt.getEditable().toString().trim();
    }

    @Override
    public String getPhoneNumber() {
        return mPhoneEdt.getEditable().toString().trim();
    }

    @Override
    public String getVerifCode() {
        return mVerifCodeEdt.getEditable().toString().trim();
    }

    @Override
    public void setAccountLogo(String url) {
//        mBankLogoImage.setWillNotDraw(false);
        ImageLoaderUtil.displayImage(mBankLogoImage, url);
    }


    @Override
    public void setUserInfo(AccountUserData data) {
        mNameEdt.setText(data.getName());
        mIDNumberEdt.setText(data.getIdNumber());
        mPhoneEdt.setText(data.getPhoneNumber());
//        mNameEdt.setEnabled(false);
//        mIDNumberEdt.setEnabled(false);
    }

    private static class TextChangeListener implements ClearEditTextNew.MyTextWatcher {

        private WeakReference<AddAccountActivity> weakReference;

        private boolean alreadyShowBankImage;

        TextChangeListener(AddAccountActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            AddAccountActivity activity = weakReference.get();
            if (activity == null) return;

            //根据银行卡前6位查询银行卡信息
            if (activity.mAccountNumberEdt.getEditable().length() == 6) {
                activity.presenter.queryCardBin();
                alreadyShowBankImage = true;
            }
            if (TextUtils.isEmpty(activity.mBankCardType) && activity.mAccountNumberEdt.getEditable().length() >= 16) {
                activity.presenter.queryCardBinFull();
            }


            //发送验证码按钮
            if (activity.mPhoneEdt.getEditable().length()
                    >= activity.getResources().getInteger(R.integer.phoneLength)) {
                if (activity.mVerifCodeSendTv.getText()
                        .toString()
                        .equals(activity.getResources().getString(R.string.send_verification_send))) {
                    activity.presenter.setEnable(activity.mVerifCodeSendTv, true);
                }
            } else {
                if (activity.mVerifCodeSendTv.getText()
                        .toString()
                        .equals(activity.getResources().getString(R.string.send_verification_send))) {
                    activity.presenter.setEnable(activity.mVerifCodeSendTv, false);
                }
            }

            //提交按钮
            activity.checkCommitButtonIsEnable();
        }
    }

    public void checkCommitButtonIsEnable() {
        if (mNameEdt.getEditable().length()
                >= getResources().getInteger(R.integer.edit_text_length_default)
                && mIDNumberEdt.getEditable().length()
                >= getResources().getInteger(R.integer.edit_text_length_default)
                && mAccountNumberEdt.getEditable().length()
                >= getResources().getInteger(R.integer.edit_text_length_default)
                && mPhoneEdt.getEditable().length()
                >= getResources().getInteger(R.integer.phoneLength)
                && mVerifCodeEdt.getEditable().length()
                >= getResources().getInteger(R.integer.verification_code_length)
                && mCheckBox.isChecked()) {
            if (!mCommitBtn.isEnabled()) {
                mCommitBtn.setEnabled(true);
            }
        } else {
            if (mCommitBtn.isEnabled()) {
                mCommitBtn.setEnabled(false);
            }
        }
    }
}
