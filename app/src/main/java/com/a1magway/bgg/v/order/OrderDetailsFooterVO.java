package com.a1magway.bgg.v.order;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseViewOwner;

import butterknife.BindView;

/**
 * Created by jph on 2017/8/19.
 */
public class OrderDetailsFooterVO extends BaseViewOwner {
    @BindView(R.id.details_txt_pay_way)
    TextView mPayWayTxt;
    @BindView(R.id.details_v_divider_11)
    View mPayWayDividerV;

    @BindView(R.id.details_txt_deliver_way)
    TextView mDeliverWayTxt;
    @BindView(R.id.details_txt_deliver_num)
    TextView mDeliverNumTxt;
    @BindView(R.id.details_v_divider_12)
    View mDeliverDividerV;

    @BindView(R.id.details_txt_remark)
    TextView mRemarkTxt;
    @BindView(R.id.details_txt_pro_price)
    TextView mProPriceTxt;
    @BindView(R.id.details_txt_tax)
    TextView mTaxTxt;
    @BindView(R.id.details_txt_freight)
    TextView mFreightTxt;
    @BindView(R.id.details_txt_total_price)
    TextView mTotalPriceTxt;
    @BindView(R.id.details_txt_pay_price)
    TextView mPayPriceTxt;
    @BindView(R.id.details_txt_total_count)
    TextView mGoodsCount;

    public OrderDetailsFooterVO(Context context) {
        super(context, R.layout.order_details_footer);
    }
}
