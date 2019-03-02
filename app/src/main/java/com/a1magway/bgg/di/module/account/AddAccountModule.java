package com.a1magway.bgg.di.module.account;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IInsertBankCard;
import com.a1magway.bgg.data.repository.NetInsertBankCard;
import com.a1magway.bgg.v.account.AddAccountContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by enid on 2018/6/6.
 */

@Module
public class AddAccountModule {
    private AddAccountContract.View mAddAccountView;

    public AddAccountModule(AddAccountContract.View mAddAccountView) {
        this.mAddAccountView = mAddAccountView;
    }

    @Provides
    public AddAccountContract.View provideAddAccountV(){
        return mAddAccountView;
    }

    @Provides
    public IInsertBankCard provideAddAccontData(APIManager apiManager){
        return new NetInsertBankCard(apiManager);
    }
}
