package com.a1magway.bgg.p.account;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.AccountBankCardData;
import com.a1magway.bgg.data.entity.BankCardData;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IAccountWithdraw;
import com.a1magway.bgg.refactor.BasePresenter;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.v.account.AccountWithdrawContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/28.
 */

public class AccountWithdrawP extends BasePresenter<AccountWithdrawContract.View> implements AccountWithdrawContract.Presenter {
    private IAccountWithdraw mIAccountWithdraw;

    @Inject
    APIManager apiManager;

    @Inject
    public AccountWithdrawP(@NonNull AccountWithdrawContract.View view, IAccountWithdraw accountWithdraw) {
        super(view);
        this.mIAccountWithdraw = accountWithdraw;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        apiManager.getBankCardList(GlobalData.getInstance().getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<List<AccountBankCardData>>bindToLifecycle())
                .subscribe(new Consumer<List<AccountBankCardData>>() {
                    @Override
                    public void accept(List<AccountBankCardData> accountBankCardData) throws Exception {
                        if (accountBankCardData != null && accountBankCardData.size() > 0) {
                            mView.setDefaultAccountNumber(accountBankCardData.get(0));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void withdraw(int userId, String money, String remark, String accountNumber, String accountType) {
        mIAccountWithdraw.withdrawal(userId, money, remark, accountNumber, accountType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<APIResponse>(getContext()) {
                    @Override
                    public void onNext(APIResponse response) {
                        Toast.makeText((Context) mView, response.getMsg(), Toast.LENGTH_LONG).show();
                        mView.getActivity().setResult(Activity.RESULT_OK);
                        mView.getActivity().finish();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void queryCardBinFull(String account_number) {
        mIAccountWithdraw.queryCardBinFull(account_number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BankCardData>(getContext()) {
                    @Override
                    public void onNext(BankCardData bankCardData) {
                        mView.setAccountLogo(bankCardData.getBanklogo());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
