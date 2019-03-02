package com.a1magway.bgg.p.login;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.BindPhoneVerificationCodeData;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.ISendVerificationData;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.refactor.AnimTransitionActivity;
import com.a1magway.bgg.refactor.BasePresenter;
import com.a1magway.bgg.util.ActivityIntentUtil;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.v.login.PasswordResetActivity;
import com.a1magway.bgg.v.login.UserBindPhoneContract;
import com.a1magway.bgg.v.login.UserVerificationContract;
import com.almagway.common.utils.ToastUtil;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by enid on 2018/8/3.
 */

public class UserBindPhoneP extends BasePresenter<UserBindPhoneContract.View> implements
        UserBindPhoneContract.Presenter {

    private CountDownTimer timer;
    private APIManager apiManager;
    private String sessionId;

    @Inject
    public UserBindPhoneP(@NonNull UserBindPhoneContract.View view, APIManager apiManager) {
        super(view);
        this.apiManager = apiManager;
    }


    @Override
    public void sendVerificationCodeToUserPhone() {
        if (mView.getPhoneString().length() != 11) {
            Toaster.showShort(getContext(), "手机号不正确");
        } else {
            apiManager.getBindPhoneVerificationCode(mView.getPhoneString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<BindPhoneVerificationCodeData>() {
                        @Override
                        public void accept(BindPhoneVerificationCodeData bindPhoneVerificationCodeData) throws Exception {
                            sessionId = bindPhoneVerificationCodeData.getData();
                            Toaster.showShort(getContext(), bindPhoneVerificationCodeData.getMsg());
                            showVerificationCodeAgainTime();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    });
        }
    }

    @Override
    public void clickBtn() {
        if (mView.getPhoneString().length() != 11) {
            ToastUtil.showShort("手机号不正确");
        } else if (mView.getVerificationCodeString().length() != 4) {
            ToastUtil.showShort("验证码不正确");
        } else {
            mView.setBtnEnable(false);
            apiManager.bindPhoneNumber(GlobalData.getInstance().getUserId(),
                    mView.getPhoneString(),
                    mView.getVerificationCodeString(),
                    sessionId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<APIResponse>() {
                        @Override
                        public void accept(APIResponse apiResponse) throws Exception {
                            if (apiResponse.getCode() == 0) {
                                //绑定成功
                                updateUserInfo();
                            } else {
                                //绑定失败
                                mView.setBtnEnable(true);
                            }
                            ToastUtil.showShort(apiResponse.getMsg());
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            mView.setBtnEnable(true);
                        }
                    });
        }
    }

    //更新用户信息保存
    private void updateUserInfo() {
        apiManager.getUserInfo(String.valueOf(GlobalData.getInstance().getUserId()))
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginData>() {
                    @Override
                    public void accept(LoginData loginData) throws Exception {
                        GlobalData.getInstance().setLoginData(loginData);
                        if (loginData.getInviterName() == null) {
                            //未绑定邀请码到绑定邀请码页面
                            ActivityIntentUtil.toInvitationGuideActivity(getContext(),"绑定邀请码",R.drawable.ic_title_close);
                        } else if (loginData.getMemberGrade() < 6) {
                            //绑定了邀请码到升级会员页
                            ActivityIntentUtil.toUpgradeGuidePager1Activity(getContext());
                        }
                        mView.finishActivity();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
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
}
