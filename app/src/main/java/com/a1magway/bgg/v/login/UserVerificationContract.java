package com.a1magway.bgg.v.login;

import com.a1magway.bgg.refactor.BaseContract;

/**
 * author: Beaven
 * date: 2017/11/1 11:57
 */

public interface UserVerificationContract {

    interface View extends BaseContract.BaseView {

        void showVerificationCodeTime(CharSequence time, boolean isEnable);

        /**
         * 获取手机号码
         */
        String getPhoneString();

        /**
         * 获取验证码
         */
        String getVerificationCodeString();

        /**
         * 获取checkBox状态
         */
        boolean getCheckBoxChecked();

        void setBtnEnable(boolean enable);
    }


    interface Presenter extends BaseContract.BasePresenter {

        void setEnable(android.view.View v, boolean isEnable);

        void onNextBtnClick(int type, String phone);

        void onCountDownTimerTvClick(int type);

        void canGoNext();

        void canGoNextForForget();

    }
}
