package com.a1magway.bgg.di.module.account;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IAccountWithdraw;
import com.a1magway.bgg.data.repository.NetAccountWithdraw;
import com.a1magway.bgg.v.account.AccountWithdrawContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by enid on 2018/6/8.
 */
@Module
public class AccountWithdrawModule {

    private AccountWithdrawContract.View mAccountWithdrawView;

    public AccountWithdrawModule(AccountWithdrawContract.View mAccountWithdrawView) {
        this.mAccountWithdrawView = mAccountWithdrawView;
    }

    @Provides
    public AccountWithdrawContract.View provideAccountWithdrawV(){
        return mAccountWithdrawView;
    }

    @Provides
    public IAccountWithdraw provideAccountWithdrawData(APIManager apiManager){
        return new NetAccountWithdraw(apiManager);
    }
}
