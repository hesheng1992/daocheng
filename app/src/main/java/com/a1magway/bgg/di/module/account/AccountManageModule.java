package com.a1magway.bgg.di.module.account;

import com.a1magway.bgg.data.entity.AccountBankCardData;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IBankCardList;
import com.a1magway.bgg.data.repository.IRemoveCard;
import com.a1magway.bgg.data.repository.NetBankCardList;
import com.a1magway.bgg.data.repository.NetRemoveCard;
import com.a1magway.bgg.v.account.AccountManageActivity;
import com.a1magway.bgg.v.account.AccountManageContract;
import com.a1magway.bgg.v.account.BankCardAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by enid on 2018/6/6.
 */

@Module
public class AccountManageModule {
    private AccountManageContract.View mAccountManageView;

    public AccountManageModule(AccountManageContract.View mAccountManageView) {
        this.mAccountManageView = mAccountManageView;
    }

    @Provides
    public AccountManageContract.View provideAccountManageV(){
        return mAccountManageView;
    }

    @Provides
    public IBankCardList provideBankCardListData(APIManager apiManager){
        return new NetBankCardList(apiManager);
    }

    @Provides
    public IRemoveCard provideRemoveCardData(APIManager apiManager) {
        return new NetRemoveCard(apiManager);
    }

    @Provides
    public BankCardAdapter provideAddressAdapter(IRemoveCard removeCardData) {
        return new BankCardAdapter(new ArrayList<AccountBankCardData>(), removeCardData, AccountManageActivity.CARD_LIST_TYPE_SELECT);
    }
}
