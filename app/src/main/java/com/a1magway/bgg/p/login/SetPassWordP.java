package com.a1magway.bgg.p.login;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.common.broadcast.LoginReceiver;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.repository.ILoginData;
import com.a1magway.bgg.data.repository.ISetPwdData;
import com.a1magway.bgg.refactor.BasePresenter;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.v.invitation.InvitationActivity;
import com.a1magway.bgg.v.login.PasswordResetActivity;
import com.a1magway.bgg.v.login.PasswordResetContract;
import com.almagway.common.utils.AesUtil;
import com.almagway.common.utils.ToastUtil;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/** 设置密码 Created by lyx on 2017/8/7. */
public class SetPassWordP extends BasePresenter<PasswordResetContract.View>
        implements PasswordResetContract.Presenter {

    private ISetPwdData mISetPwdData;
    private ILoginData mILoginData;

    @Inject
    public SetPassWordP(
            PasswordResetContract.View iSetPassWordV, ISetPwdData iSetPwdData, ILoginData data) {
        super(iSetPassWordV);
        mISetPwdData = iSetPwdData;
        mILoginData = data;
    }

    @Override
    public void onNextBtnClick(final String phone, final int type) {
        final String pwd1 = mView.getEditable(0).toString();
        String pwd2 = mView.getEditable(1).toString();
        if (!pwd1.equals(pwd2)) {
            Toaster.showShort(getContext(), "密码不一致!");
            return;
        }
        mISetPwdData
                .setPwd(phone, AesUtil.encryptAES(pwd1), type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<String>bindToDestroyEvent())
                .subscribe(
                        new SimpleObserver<String>(getContext()) {
                            @Override
                            public void onNext(String s) {
                                super.onNext(s);
                                // 注册
                                if (type == 0) {
                                    new AlertDialog.Builder(getContext())
                                            .setMessage(
                                                    getContext()
                                                            .getString(
                                                                    R.string
                                                                            .set_pwd_auto_login_tip))
                                            .setNegativeButton(
                                                    getContext().getString(R.string.common_cancel),
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(
                                                                DialogInterface dialog, int which) {
                                                            mView.getActivity().finish();
                                                        }
                                                    })
                                            .setPositiveButton(
                                                    getContext().getString(R.string.common_ensure),
                                                    new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(
                                                                DialogInterface dialog, int which) {
                                                            Login(phone, pwd1);
                                                        }
                                                    })
                                            .create()
                                            .show();
                                } else {
                                    ToastUtil.showShort(
                                            getContext().getString(R.string.setting_success));
                                    mView.getActivity().finish();
                                }
                            }
                        });
    }

    /** 输入检查 */
    @Override
    public void canGoNext() {
        int length1 = mView.getEditable(0).length();
        int length2 = mView.getEditable(1).length();
        if (length1 >= getContext().getResources().getInteger(R.integer.pwd_min_length)
                && length2 >= getContext().getResources().getInteger(R.integer.pwd_min_length)
                && length1 == length2) {
            mView.setBtnEnable(true);
        } else {
            mView.setBtnEnable(false);
        }
    }

    private void Login(String phone, String pwd1) {
        mView.showLoading(getContext().getString(R.string.set_pwd_auto_login_msg));
        mILoginData
                .login(phone, AesUtil.encryptAES(pwd1))
                .compose(SetPassWordP.this.<LoginData>bindToDestroyEvent())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new BaseObserver<LoginData>(getContext()) {
                            @Override
                            public void onNext(@NonNull LoginData loginData) {
                                mView.hideLoading();
                                GlobalData.getInstance().setLoginData(loginData);
                                LoginReceiver.sendLoginStatusBroadcast(
                                        getContext(), LoginReceiver.LOG_IN);
                                mView.getActivity().setResult(Activity.RESULT_OK);
                                ((PasswordResetActivity) mView.getActivity()).finishWithAnim();
                                //登录成功后如果没有绑定邀请码弹出绑定邀请码的界面
                                if (loginData.getMemberGrade() == 1) {
                                    InvitationActivity.start(mView.getActivity());
                                }
                            }

                            @Override
                            public void onComplete() {}
                        });
    }
}
