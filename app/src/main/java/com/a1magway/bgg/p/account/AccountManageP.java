package com.a1magway.bgg.p.account;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.AccountBankCardData;
import com.a1magway.bgg.data.repository.IBankCardList;
import com.a1magway.bgg.data.repository.IRemoveCard;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.v.account.AccountManageActivity;
import com.a1magway.bgg.v.account.AccountManageContract;
import com.a1magway.bgg.v.account.BankCardAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/28.
 */

public class AccountManageP extends BaseLoadP<List<AccountBankCardData>, AccountManageContract.View> implements AccountManageContract.Presenter {

    private IBankCardList mIBankCardListData;

    private IRemoveCard mIRemoveCardData;

    private BankCardAdapter mBankCardAdapter;

    @Inject
    public AccountManageP(@NonNull AccountManageContract.View view, IBankCardList iBankCardList,
                          IRemoveCard iRemoveCard, BankCardAdapter bankCardAdapter) {
        super(view);
        this.mIBankCardListData = iBankCardList;
        this.mIRemoveCardData = iRemoveCard;
        this.mBankCardAdapter = bankCardAdapter;
    }


    @Nullable
    @Override
    public Observable<List<AccountBankCardData>> getDataObservable() {
        return mIBankCardListData.getBankCardList(GlobalData.getInstance().getUserId());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mView.setRecyclerViewAdapter(mBankCardAdapter);
        mBankCardAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, final int position) {
             if (mView.getStartType().equals(AccountManageActivity.CARD_LIST_TYPE_SELECT)){
                    Intent intent = new Intent();
                    intent.putExtra("data",mBankCardAdapter.getList().get(position));
                    mView.getActivity().setResult(Activity.RESULT_OK,intent);
                    mView.getActivity().finish();
                }
            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        });
        mView.setLoadable(false);
        loadData();
    }

    @Override
    protected void onLoadSuccess(List<AccountBankCardData> accountBankCardData) {
        super.onLoadSuccess(accountBankCardData);
        mBankCardAdapter.setList(accountBankCardData);
        checkShowNoneData(accountBankCardData);
    }

    @Override
    protected void onLoadFinish() {
        super.onLoadFinish();
        mView.stopRefresh();
    }

    public void removeCard(int id) {
        mIRemoveCardData.removeCard(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<APIResponse>(getContext()) {
                    @Override
                    public void onNext(APIResponse response) {
                        if (response.isSuccess()) {
                            Toaster.showShort(getContext(), response.getMsg());
                            loadData(false);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void selectBankResult(AccountBankCardData accountBankCardData) {
        Intent intent = new Intent();
        intent.putExtra("data",accountBankCardData);
        mView.getActivity().setResult(Activity.RESULT_OK,intent);
        mView.getActivity().finish();
    }

    class BankCardTestAdapter extends RecyclerView.Adapter<BankCardTestAdapter.ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account_swipe, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 3;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
