package com.a1magway.bgg.p.account;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.AccountUserData;
import com.a1magway.bgg.data.entity.BankCardData;
import com.a1magway.bgg.data.repository.IInsertBankCard;
import com.a1magway.bgg.refactor.BasePresenter;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.v.account.AddAccountContract;
import com.almagway.common.utils.ToastUtil;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/28.
 */

public class AddAccountP extends BasePresenter<AddAccountContract.View> implements AddAccountContract.Presenter {

    private IInsertBankCard mIInsertBankCardData;
    private BankCardData mBankCardData;
    private CountDownTimer timer;
    private String mSessionId = "";

    @Inject
    public AddAccountP(@NonNull AddAccountContract.View view, IInsertBankCard iInsertBankCard) {
        super(view);
        mIInsertBankCardData = iInsertBankCard;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    public void queryCardBin() {
        final String bankCardNumber = mView.getBankCardNumber();
        String substring = bankCardNumber.substring(0, 6);
        mIInsertBankCardData.queryCardBin(substring)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BankCardData>(getContext()) {
                    @Override
                    public void onNext(BankCardData bankCardData) {
                        mBankCardData = bankCardData;
                        mView.setAccountType(bankCardData.getBankname());
                        mView.setAccountLogo(bankCardData.getBanklogo());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    public void queryCardBinFull() {
        String bankCardNumber = mView.getBankCardNumber();
        mIInsertBankCardData.queryCardBinFull(bankCardNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BankCardData>(getContext()) {
                    @Override
                    public void onNext(BankCardData bankCardData) {
                        mBankCardData = bankCardData;
                        mView.setAccountType(bankCardData.getBankname());
                        mView.setAccountLogo(bankCardData.getBanklogo());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void sendVerification() {
        String phoneNumber = mView.getPhoneNumber();
        if (TextUtils.isEmpty(phoneNumber)) {
            ToastUtil.showShort("手机号码不能为空");
            return;
        }
        mIInsertBankCardData.sendVerification(phoneNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<String>(getContext()) {
                    @Override
                    public void onNext(String sessionId) {
                        mSessionId = sessionId;
                        Toaster.showShort(getContext(), "发送验证码成功");
                        showVerificationCodeAgainTime();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void userInfo() {
        mIInsertBankCardData.userInfo(GlobalData.getInstance().getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<AccountUserData>(getContext()) {
                    @Override
                    public void onNext(AccountUserData accountUserData) {
                        if (accountUserData != null && !TextUtils.isEmpty(accountUserData.getName())
                                && !TextUtils.isEmpty(accountUserData.getIdNumber())) {
                            mView.setUserInfo(accountUserData);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void insertBankCard() {
        if (mBankCardData == null) {
            Toaster.showShort(getContext(), "没有该银行卡信息，请确认后重新输入");
            return;
        }
        int userid = GlobalData.getInstance().getUserId();
        final String realname = mView.getName();
        String bank_name = mBankCardData.getBankname();
        String idNumber = mView.getIDCardNumber();
        String accountNumber = mView.getBankCardNumber();
        String accountType = mBankCardData.getCardtype();
        String abbreviation = mBankCardData.getAbbreviation();
        String phoneNumber = mView.getPhoneNumber();
        String verfiCode = mView.getVerifCode();
        if (TextUtils.isEmpty(mSessionId)) {
            ToastUtil.showShort("验证码sessionId 为空");
            return;
        }
        mIInsertBankCardData.insertBankCard(userid, realname, bank_name, idNumber,
                accountNumber, accountType, abbreviation, phoneNumber, verfiCode, mSessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<APIResponse>(getContext()) {
                    @Override
                    public void onNext(APIResponse response) {
                        ToastUtil.showShort(response.getMsg());
                        if (response.isSuccess()) {
                            Toaster.showShort(getContext(), response.getMsg());
                            mView.getActivity().setResult(Activity.RESULT_OK);
                            mView.getActivity().finish();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void showVerificationCodeAgainTime() {
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String str = String.format(Locale.getDefault(), "%d秒后重发",
                        millisUntilFinished / 1000);
                mView.showVerificationCodeTime(str, false);
            }


            @Override
            public void onFinish() {
                String str = getContext().getResources().getString(R.string.send_verification_send);
                SpannableString content = new SpannableString(str);
                content.setSpan(new UnderlineSpan(), 0, str.length(), 0);
                mView.showVerificationCodeTime(content, true);
            }
        };
        timer.start();
    }

    public void setEnable(View v, boolean isEnable) {
        if (v.isEnabled() != isEnable) {
            v.setEnabled(isEnable);
        }
    }

}
