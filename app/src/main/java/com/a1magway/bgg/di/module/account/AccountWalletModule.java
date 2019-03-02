package com.a1magway.bgg.di.module.account;

import com.a1magway.bgg.data.entity.AccountDetailData;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IAccountData;
import com.a1magway.bgg.data.repository.NetAccountData;
import com.a1magway.bgg.v.account.AccountDetailAdapter;
import com.a1magway.bgg.v.account.MyWalletContract;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by enid on 2018/6/12.
 */
@Module
public class AccountWalletModule {
    private MyWalletContract.View mAccountWalletView;

    public AccountWalletModule(MyWalletContract.View mAccountWalletView) {
        this.mAccountWalletView = mAccountWalletView;
    }

    @Provides
    public MyWalletContract.View provideAccountWalletVew(){
        return mAccountWalletView;
    }

    @Provides
    public IAccountData provideAccountData(APIManager apiManager){
        return new NetAccountData(apiManager);
    }

    @Provides
    public AccountDetailAdapter provideAccountDetailAdapter(){
        return new AccountDetailAdapter(new ArrayList<AccountDetailData>());
    }
}
