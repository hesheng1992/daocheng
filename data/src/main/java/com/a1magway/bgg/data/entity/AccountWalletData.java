package com.a1magway.bgg.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enid on 2018/6/12.
 */

public class AccountWalletData {
    AccountDataBean userAccount;
    List<AccountDetailData> userGeneral = new ArrayList<>();

    public AccountDataBean getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(AccountDataBean userAccount) {
        this.userAccount = userAccount;
    }

    public List<AccountDetailData> getUserGeneral() {
        return userGeneral;
    }

    public void setUserGeneral(List<AccountDetailData> userGeneral) {
        this.userGeneral = userGeneral;
    }
}
