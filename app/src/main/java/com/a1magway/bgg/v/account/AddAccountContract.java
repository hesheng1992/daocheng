package com.a1magway.bgg.v.account;

import com.a1magway.bgg.data.entity.AccountUserData;
import com.a1magway.bgg.refactor.BaseContract;

/**
 * Created by enid on 2018/6/6.
 */

public interface AddAccountContract {
    interface View extends BaseContract.BaseView{
        void showVerificationCodeTime(CharSequence time, boolean isEnable);

        String getName();

        String getIDCardNumber();

        void setAccountType(String accountType);

        String getBankCardNumber();

        String getPhoneNumber();

        String getVerifCode();

        void setAccountLogo(String url);

        void setUserInfo(AccountUserData data);

    }

    interface Presenter extends BaseContract.BasePresenter {

//        void bindInvitationCode();

    }
}
