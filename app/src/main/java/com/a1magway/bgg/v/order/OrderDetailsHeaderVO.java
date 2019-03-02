package com.a1magway.bgg.v.order;

import android.content.Context;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseViewOwner;

import butterknife.BindView;

/**
 * Created by jph on 2017/8/19.
 */
public class OrderDetailsHeaderVO extends BaseViewOwner {
    @BindView(R.id.details_txt_name)
    TextView mNameTxt;
    @BindView(R.id.details_txt_phone)
    TextView mPhoneTxt;
    @BindView(R.id.details_txt_address)
    TextView mAddressTxt;
    @BindView(R.id.details_txt_postcode)
    TextView mPostcodeTxt;
    @BindView(R.id.details_txt_code)
    TextView mCodeTxt;
    @BindView(R.id.details_txt_time)
    TextView mTimeTxt;

    public OrderDetailsHeaderVO(Context context) {
        super(context, R.layout.order_details_header);
    }
}
