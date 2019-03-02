package com.a1magway.bgg.v.order;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseViewOwner;

import butterknife.BindView;

/**
 * Created by jph on 2017/8/19.
 */
public class OrderCommitHeaderVO extends BaseViewOwner {
    @BindView(R.id.commit_txt_name)
    TextView mNameTxt;
    @BindView(R.id.commit_txt_phone)
    TextView mPhoneTxt;
    @BindView(R.id.commit_txt_address)
    TextView mAddressTxt;
    @BindView(R.id.commit_txt_postcode)
    TextView mPostcodeTxt;
    @BindView(R.id.commit_txt_select_address)
    TextView mSelectAddressTxt;
    @BindView(R.id.commit_v_divider_2)
    View mAddressInfoDividerV;

    @BindView(R.id.commit_txt_add_address)
    TextView mAddAddressTxt;
    @BindView(R.id.commit_layout_add_address)
    ViewGroup mAddAddressLayout;

    //海外购物
    @BindView(R.id.commit_edit_input_identity)
    EditText commitEditInputIdentity;
    @BindView(R.id.foreign_deliver_notice)
    TextView foreignDeliverNotice;
    @BindView(R.id.commit_userInfo_cl)
    ConstraintLayout commitUserInfoCl;
    @BindView(R.id.real_name)
    EditText realName;
    @BindView(R.id.haiwai_checkbox)
    CheckedTextView haiwai_checkbox;


    public OrderCommitHeaderVO(Context context) {
        super(context, R.layout.order_commit_header);
    }
}
