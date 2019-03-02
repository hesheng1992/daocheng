package com.a1magway.bgg.v.account;

import android.view.ViewGroup;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.AccountBankCardData;
import com.a1magway.bgg.data.repository.IRemoveCard;

import java.util.List;

/**
 * Created by enid on 2018/6/7.
 */

public class BankCardAdapter extends BaseRecyclerAdapter<BankCardVH,AccountBankCardData>{
    private IRemoveCard mRemoveCard;
    private String mStartType;
    public BankCardAdapter(List<AccountBankCardData> list, IRemoveCard iRemoveCard,String startType) {
        super(list);
        this.mRemoveCard = iRemoveCard;
        this.mStartType = startType;
    }
//    public BankCardAdapter(List<AccountBankCardData> list, String startType) {
//        super(list);
//        this.mStartType = startType;
//    }

    @Override
    public BankCardVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new BankCardVH(parent,mRemoveCard,mStartType);
    }

    @Override
    public void onRealBindViewHolder(BankCardVH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        holder.showViewContent(getItem(position),position,this);
    }


}
