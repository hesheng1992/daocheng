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
 * Created by enid on 2018/6/13.
 */
@Module
public class MyWalletModule {
    private MyWalletContract.View mWalletView;

    public MyWalletModule(MyWalletContract.View mWalletView) {
        this.mWalletView = mWalletView;
    }

    @Provides
    public MyWalletContract.View provideMyWalletView(){
        return mWalletView;
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
