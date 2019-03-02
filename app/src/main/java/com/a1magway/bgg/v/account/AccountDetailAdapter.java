package com.a1magway.bgg.v.account;

import android.view.ViewGroup;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.AccountDetailData;

import java.util.List;

/**
 * Created by enid on 2018/6/15.
 */

public class AccountDetailAdapter  extends BaseRecyclerAdapter<AccountDetailVH,AccountDetailData>{
    public AccountDetailAdapter(List<AccountDetailData> list) {
        super(list);
    }

    @Override
    public AccountDetailVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new AccountDetailVH(parent);
    }

    @Override
    public void onRealBindViewHolder(AccountDetailVH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        holder.showViewContent(getItem(position));
    }
}
