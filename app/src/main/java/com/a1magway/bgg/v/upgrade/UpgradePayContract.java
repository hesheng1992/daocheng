package com.a1magway.bgg.v.upgrade;

import com.a1magway.bgg.refactor.BaseContract;

/**
 * Created by enid on 2018/8/4.
 */

public interface UpgradePayContract {
    interface View extends BaseContract.BaseView {

        /**
         * 提示绑定手机的对话框
         */
        void showBindPhoneDialog();

        /**
         * 提示升级成功的对话框
         */
        void showUpgradeSucceedDialog();
    }


    interface Presenter extends BaseContract.BasePresenter {



        /**
         * 去微信支付
         */
        void toWeixinPay();

        /**
         * 去支付宝支付
         */
        void toAliPay();
    }
}
