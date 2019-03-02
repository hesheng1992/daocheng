package com.a1magway.bgg.v.account;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.AccountDetailData;
import com.a1magway.bgg.util.GlobalData;
import com.almagway.common.utils.ApplicationUtil;

import butterknife.BindView;

/**
 * Created by enid on 2018/6/15.
 */

public class AccountDetailVH extends BaseRecyclerVH<AccountDetailData> {

    @BindView(R.id.item_account_detail_name_tv)
    TextView mTitleTv;
    @BindView(R.id.item_account_detail_time_tv)
    TextView mTimeTv;
    @BindView(R.id.item_account_detail_money_tv)
    TextView mMoneyTv;

    @BindView(R.id.item_account_detail_status)
    TextView itemAccountDetailStatus;
    @BindView(R.id.item_account_detail_order_number)
    TextView itemAccountDetailOrderNumber;
    @BindView(R.id.item_account_arrow_enable)
    ImageView item_account_arrow_enable;

    public AccountDetailVH(@NonNull ViewGroup parent) {
        super(parent, R.layout.item_accouont_detail);
    }

    @Override
    public void showViewContent(AccountDetailData accountDetailData) {
        super.showViewContent(accountDetailData);
//        1.商品销售 2.存款提现 3.提成扣除
        String businessType = "";
        switch (accountDetailData.getBusinessType()) {
            case 1:
                businessType = "商品销售";
                break;
            case 2:
                businessType = "存款提现";
                break;
            case 3:
                businessType = "提成扣除";
                break;
        }
        mTitleTv.setText(businessType);
        mTimeTv.setText(accountDetailData.getCreateDate().substring(0, 10));
        if (accountDetailData.getBusinessType() == 1) {
            mMoneyTv.setText(String.format(ApplicationUtil.getContext().getString(R.string.format_price_plus), accountDetailData.getAmount()));
        } else {
            mMoneyTv.setText(String.format(ApplicationUtil.getContext().getString(R.string.format_price_minus), accountDetailData.getAmount()));
        }
        //1.未收货 2.已收货
        if (accountDetailData.getStatus() == null) {
            return;
        }
        String status = "";
        item_account_arrow_enable.setVisibility(View.VISIBLE);
        switch (accountDetailData.getStatus()) {
            case "1":
                if (accountDetailData.getBusinessType() == 2) {
                    status = "提现中";
                    item_account_arrow_enable.setVisibility(View.INVISIBLE);
                } else {
                    status = "未收货";
                }
                break;
            case "2":
                if (accountDetailData.getBusinessType() == 2) {
                    status = "已提现";
                    item_account_arrow_enable.setVisibility(View.INVISIBLE);
                } else {
                    status = "已收货";
                }
                break;
        }
        itemAccountDetailStatus.setText(status);
        if (accountDetailData.getOrderNum()!=null) {
            itemAccountDetailOrderNumber.setText(String.valueOf("订单号:" + accountDetailData.getOrderNum()));
        }else {
            itemAccountDetailOrderNumber.setText("");
        }
    }
}
