package com.a1magway.bgg.v.login;

import com.a1magway.bgg.refactor.BaseContract;

/**
 * Created by enid on 2018/8/3.
 */

public interface UserBindPhoneContract {
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
         *设置确定按钮是否可点击
         */
        void setBtnEnable(boolean enable);


        void finishActivity();
    }


    interface Presenter extends BaseContract.BasePresenter {

        /**
         * 发送验证码到用户手机
         */
        void sendVerificationCodeToUserPhone();

        /**
         * 点击确定
         */
        void clickBtn();
    }
}
