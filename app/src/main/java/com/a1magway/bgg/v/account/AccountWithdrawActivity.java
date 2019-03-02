package com.a1magway.bgg.v.account;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.AccountBankCardData;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.account.DaggerAccountWithdrawComponent;
import com.a1magway.bgg.di.module.account.AccountWithdrawModule;
import com.a1magway.bgg.p.account.AccountWithdrawP;
import com.a1magway.bgg.refactor.PresenterActivity;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.v.web.WebActivity;
import com.a1magway.bgg.widget.ClearEditTextNew;
import com.almagway.common.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * 账户提现
 */
public class AccountWithdrawActivity extends PresenterActivity<AccountWithdrawP> implements AccountWithdrawContract.View {

    @BindView(R.id.withdraw_money_edt)
    ClearEditTextNew mWithdrawEdt;

    @BindView(R.id.withdraw_bank_tv)
    TextView mBankNameTv;
    private AccountBankCardData mSelectedBankCard;

    @BindView(R.id.withdraw_bank_img)
    ImageView mBankImge;

    @BindView(R.id.withdraw_agreement_tv)
    TextView mAgreementTv;

    @BindView(R.id.withdraw_checkBox)
    CheckBox mCheckBox;

    @BindView(R.id.withdraw_commit_btn)
    Button mWithdrawCommitBtn;

    @OnClick({R.id.withdraw_bank_tv, R.id.withdraw_agreement_tv, R.id.withdraw_commit_btn})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.withdraw_bank_tv:
                AccountManageActivity.startForResult(this, AccountManageActivity.CARD_LIST_TYPE_SELECT, 100);
                break;
            case R.id.withdraw_agreement_tv:
                WebActivity.start(this, getString(R.string.personal_contact_buy_notice_url));
                break;
            case R.id.withdraw_commit_btn:
                if (mSelectedBankCard == null) {
                    ToastUtil.showLong("请添加提现账户");
                    return;
                }
                if (isNumberAndNoZero(mWithdrawEdt.getEditable().toString().trim())) {
                    try {
                        String accountMoney = mWithdrawEdt.getEditable().toString().trim();
                        double count;
                        if (accountMoney.length()>=11){
                            count = Double.valueOf(accountMoney.substring(0,11));
                        }else {
                            count = Double.valueOf(accountMoney);
                        }
                        if (count < 10) {
                            Toaster.showLong(this,"提现金额不能小于10元");
//                            ToastUtil.showLong("提现金额不能小于10元");
                            return;
                        }
                    } catch (Exception e) {
                        Toaster.showLong(this,"请输入正确的提现金额");
//                        ToastUtil.showLong("请输入正确的提现金额");
                        return;
                    }
                    presenter.withdraw(GlobalData.getInstance().getUserId(),
                            mWithdrawEdt.getEditable().toString().trim(),
                            "",
                            mSelectedBankCard.getAccountNumber(),
                            mSelectedBankCard.getAccountOwner()
                    );
                } else if (mWithdrawEdt.getEditable().toString().trim().matches("[0]+\\.*[0]*")) {
                    Toaster.showLong(this,"提现金额不能小于10元");
//                    ToastUtil.showLong("提现金额不能小于10元");
                } else {
                    Toaster.showLong(this,"请输入正确的提现金额");
//                    ToastUtil.showLong("请输入正确的提现金额");
                }
                break;
        }
    }

    private boolean isNumberAndNoZero(String s) {
        //为数字和小数且不能为0
        return s.matches("[0-9]+\\.*[0-9]*") && !s.matches("[0]+\\.*[0]*");
    }

    @OnCheckedChanged(R.id.withdraw_checkBox)
    void onCheckChanged() {
        if (mCheckBox.isChecked()) {
            mWithdrawCommitBtn.setEnabled(true);
        } else {
            mWithdrawCommitBtn.setEnabled(false);
        }

    }

    public static final void startForResult(Context context, int requestCode) {
        Intent intent = new Intent(context, AccountWithdrawActivity.class);
        JumpUtil.startActivityForResult(context, intent, requestCode);
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerAccountWithdrawComponent.builder()
                .appComponent(appComponent)
                .accountWithdrawModule(new AccountWithdrawModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_account_withdraw;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        setImageBack(true, R.drawable.ic_arrow_left);
        setTextTitle("账户提现");
        mAgreementTv.setText(Html.fromHtml(getResources().getString(
                R.string.add_account_withdraw_agreement)));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (data == null) return;
            mSelectedBankCard = (AccountBankCardData) data.getSerializableExtra("data");
            mBankNameTv.setText(mSelectedBankCard.getAccountNumber());
            presenter.queryCardBinFull(mSelectedBankCard.getAccountNumber());

        }
    }

    @Override
    public void setAccountLogo(String imgUrl) {
        ImageLoaderUtil.displayImage(mBankImge, imgUrl);
    }

    @Override
    public void setDefaultAccountNumber(AccountBankCardData data) {
        if (data == null) return;
        mSelectedBankCard = data;
        mBankNameTv.setText(data.getAccountNumber());
        presenter.queryCardBinFull(data.getAccountNumber());
    }

}
