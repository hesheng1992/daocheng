package com.a1magway.bgg.v.account;

import com.a1magway.bgg.data.entity.AccountBankCardData;
import com.a1magway.bgg.refactor.BaseContract;

/**
 * Created by enid on 2018/6/6.
 */

public interface AccountWithdrawContract {
    interface View extends BaseContract.BaseView{
       void setAccountLogo(String imgUrl);
       void setDefaultAccountNumber(AccountBankCardData data);
    }

    interface Presenter extends BaseContract.BasePresenter {

        void withdraw(int userId,
                      String money,
                      String remark,
                      String accountNumber,
                      String accountType);

    }
}
