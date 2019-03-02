package com.a1magway.bgg.p.login;

import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.repository.ISendVerificationData;
import com.a1magway.bgg.refactor.BasePresenter;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.v.login.PasswordResetActivity;
import com.a1magway.bgg.v.login.UserVerificationContract;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import java.util.Locale;
import javax.inject.Inject;

/**
 * Created by lyx on 2017/8/1.
 */
public class UserVerificationP extends BasePresenter<UserVerificationContract.View>
    implements UserVerificationContract.Presenter {

    //注册
    public static final int CODE_TYPE_REGISTER = 0;
    //忘记密码
    public static final int CODE_TYPE_FORGET = 1;

    private ISendVerificationData mData;
    private CountDownTimer timer;


    @Inject
    public UserVerificationP(UserVerificationContract.View iSendVerificationCodeV, ISendVerificationData iSendVerificationData) {
        super(iSendVerificationCodeV);
        mData = iSendVerificationData;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }


    /**
     * 倒计时点击事件
     */
    @Override
    public void onCountDownTimerTvClick(int type) {
        mData.sendVerification(mView.getPhoneString(), type)
            .subscribe(new BaseObserver<String>(getContext()) {
                @Override
                public void onError(@NonNull Throwable e) {
                    super.onError(e);
                }


                @Override
                public void onNext(@NonNull String msg) {
                    Toaster.showShort(getContext(), msg);
                    showVerificationCodeAgainTime();
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


    /**
     * 发送验证验证码请求
     */
    @Override
    public void onNextBtnClick(final int type, final String phone) {
        mData.checkCode(mView.getPhoneString(), mView.getVerificationCodeString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(this.<APIResponse>bindToDestroyEvent())
            .subscribe(new BaseObserver<APIResponse>(getContext()) {

                @Override
                public void onNext(@NonNull APIResponse apiResponse) {
                    PasswordResetActivity.startSetPassWordActivity(mView.getActivity(), phone, type);
                    mView.getActivity().finish();
                }


                @Override
                public void onComplete() {

                }
            });
    }


    /**
     * 输入检查
     */
    @Override
    public void canGoNext() {
        if (mView.getPhoneString().length() >=
            getContext().getResources().getInteger(R.integer.phoneLength)
            && mView.getVerificationCodeString().length() >=
            getContext().getResources().getInteger(R.integer.verification_code_length)
            && mView.getCheckBoxChecked()) {
            mView.setBtnEnable(true);
        } else {
            mView.setBtnEnable(false);
        }
    }


    /**
     * 输入检查（忘记密码）
     */
    @Override
    public void canGoNextForForget() {
        if (mView.getPhoneString().length() >=
            getContext().getResources().getInteger(R.integer.phoneLength)
            && mView.getVerificationCodeString().length() >=
            getContext().getResources().getInteger(R.integer.verification_code_length)) {
            mView.setBtnEnable(true);
        } else {
            mView.setBtnEnable(false);
        }
    }


    @Override
    public void setEnable(View v, boolean isEnable) {
        if (v.isEnabled() != isEnable) {
            v.setEnabled(isEnable);
        }
    }
}
