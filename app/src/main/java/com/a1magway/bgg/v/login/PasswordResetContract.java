package com.a1magway.bgg.v.login;

import android.text.Editable;
import com.a1magway.bgg.refactor.BaseContract;

/**
 * author: Beaven
 * date: 2017/11/1 19:41
 */

public interface PasswordResetContract {

    interface View extends BaseContract.BaseLoadView {

        Editable getEditable(int index);

        void setBtnEnable(boolean isEnable);
    }


    interface Presenter extends BaseContract.BasePresenter {

        void onNextBtnClick(String phone, int type);

        void canGoNext();

    }
}
