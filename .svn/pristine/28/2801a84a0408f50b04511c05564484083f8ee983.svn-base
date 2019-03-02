package com.a1magway.bgg.di.module;


import com.a1magway.bgg.v.upgrade.UpgradePayContract;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by enid on 2018/8/4.
 */

@Module
public class UpgradePayModel {
    private UpgradePayContract.View view;
    private int pay_money;
    public UpgradePayModel(UpgradePayContract.View view,int pay_money){
        this.view =view;
        this.pay_money = pay_money;
    }

    @Provides
    public UpgradePayContract.View provideUpgradePayView(){
        return view;
    }

    @Named("payMoney")
    @Provides
    public int providePayMoney(){
        return pay_money;
    }
}
