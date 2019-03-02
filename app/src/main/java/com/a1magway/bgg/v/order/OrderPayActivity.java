package com.a1magway.bgg.v.order;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.order.DaggerOrderPayComponent;
import com.a1magway.bgg.di.module.order.OrderPayModule;
import com.a1magway.bgg.p.order.OrderPayP;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.util.code.RequestCode;
import com.a1magway.bgg.widget.TitleBar;
import com.almagway.common.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderPayActivity extends PActivity<OrderPayP> implements IOrderPayV {

    private static final int WEB_REULT_CODE=101;

    @BindView(R.id.order_pay_title_bar)
    TitleBar mTitleBar;

    @BindView(R.id.order_pay_once_layout)
    LinearLayout mPayOnceLayout;

    @BindView(R.id.order_pay_check_weixin)
    CheckBox mPayWeixinCheckBox;

    @BindView(R.id.order_pay_check_alipay)
    CheckBox mPayAlipayCheckBox;

    @BindView(R.id.order_pay_check_unionpay)
    CheckBox mPayUnionpayCheckBox;

    @BindView(R.id.order_pay_divide_layout)
    LinearLayout mPayDivideLayout;

    @BindView(R.id.order_pay_bank_card_text)
    TextView mBankCardText;

    @BindView(R.id.order_pay_bank_card_img)
    ImageView mBankCardImage;

    @BindView(R.id.order_pay_divide_count_text)
    TextView mDivideCountText;

    @BindView(R.id.order_pay_original_price)
    TextView mPriceOriginalTv;

    @BindView(R.id.order_pay_discount)
    TextView mPriceDiscountTv;

    @BindView(R.id.order_pay_deliver_price)
    TextView mPriceDeliverTv;

    @BindView(R.id.order_pay_tariff)
    TextView mPriceTraiffTv;

    @BindView(R.id.order_count_all)
    TextView mCountAllTv;

    @BindView(R.id.order_pay_real_price)
    TextView mPriceRealTv;

    @BindView(R.id.order_pay_account_price)
    TextView mPriceAccountTv;


    @BindView(R.id.order_pay_layout_weixin)
    LinearLayout mPayWinXin;

    @BindView(R.id.order_pay_layout_alipay)
    LinearLayout mPayWinAlipay;

    @BindView(R.id.order_pay_layout_unionpay)
    LinearLayout mPayWinUnionpay;

    @BindView(R.id.order_pay_once_tv)
    TextView mPayOnceTv;

    @BindView(R.id.order_pay_divide_tv)
    TextView mPayDivideTv;

    private int mPayType = 0;

    private int orderId = 0;

    private String orderNum="";

    @OnClick({R.id.order_pay_buy,
            R.id.order_pay_once_tv,
            R.id.order_pay_divide_tv,
            R.id.order_pay_layout_weixin,
            R.id.order_pay_layout_alipay,
            R.id.order_pay_layout_unionpay,
            R.id.order_pay_bank_card_select,
            R.id.order_pay_divide_count_select
    })
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.order_pay_buy:
                mPresenter.clickPay(mPayType,orderId);
                break;
            case R.id.order_pay_once_tv:
                mPayType = 0;
                mPayOnceLayout.setVisibility(View.VISIBLE);
                mPayDivideLayout.setVisibility(View.INVISIBLE);
                mPayOnceTv.setTextColor(getResources().getColor(R.color.white));
                mPayOnceTv.setBackgroundColor(getResources().getColor(R.color.black));
                mPayDivideTv.setTextColor(getResources().getColor(R.color.black));
                mPayDivideTv.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.order_pay_divide_tv:
                mPayType = 1;
                mPayOnceLayout.setVisibility(View.INVISIBLE);
                mPayDivideLayout.setVisibility(View.VISIBLE);
                mPayOnceTv.setTextColor(getResources().getColor(R.color.black));
                mPayOnceTv.setBackgroundColor(getResources().getColor(R.color.white));
                mPayDivideTv.setTextColor(getResources().getColor(R.color.white));
                mPayDivideTv.setBackgroundColor(getResources().getColor(R.color.black));
                break;
            case R.id.order_pay_layout_weixin:
                mPayWeixinCheckBox.setChecked(true);
                mPayAlipayCheckBox.setChecked(false);
                mPayUnionpayCheckBox.setChecked(false);
                break;
            case R.id.order_pay_layout_alipay:
                mPayWeixinCheckBox.setChecked(false);
                mPayAlipayCheckBox.setChecked(true);
                mPayUnionpayCheckBox.setChecked(false);
                break;
            case R.id.order_pay_layout_unionpay:
                mPayWeixinCheckBox.setChecked(false);
                mPayAlipayCheckBox.setChecked(false);
                mPayUnionpayCheckBox.setChecked(true);
                break;
            case R.id.order_pay_bank_card_select:
                ToastUtil.showShort("选择信用卡");
                break;
            case R.id.order_pay_divide_count_select:
                ToastUtil.showShort("选择分期方式");
                break;
        }
    }

//    private static final String EXTRA_ORDER_ITEM_NAME = "extra_order_item_name";
    private static final String EXTRA_ORDER_NUM_NAME = "extra_order_num_name";

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_order_pay;
    }

//    public static final void start(Context context, OrderItem orderItem) {
//        Intent intent = new Intent(context, OrderPayActivity.class);
//        intent.putExtra(EXTRA_ORDER_ITEM_NAME, orderItem);
//        JumpUtil.startActivity(context, intent);
//    }

    public static final void start(Context context,String orderNum,int orderId) {
        Intent intent = new Intent(context, OrderPayActivity.class);
        intent.putExtra(EXTRA_ORDER_NUM_NAME, orderNum);
        intent.putExtra("orderId", orderId);
        JumpUtil.startActivity(context, intent);
    }


    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        orderNum =  getIntent().getStringExtra(EXTRA_ORDER_NUM_NAME);
        orderId = getIntent().getIntExtra("orderId",0);
        DaggerOrderPayComponent.builder()
                .appComponent(appComponent)
                .orderPayModule(new OrderPayModule(this, orderNum))
                .build().inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTitleBar.setLeftImgClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RequestCode.REQUEST_CODE_UNIONPAY_SUCCESS){
            mPresenter.unionPayResult();
            EventBus.getDefault().post(RequestCode.REQUEST_CODE_UNIONPAY_SUCCESS);
            finish();
        }
    }

    @Override
    public boolean isOnceWeixinPay() {
        return mPayWeixinCheckBox.isChecked();
    }

    @Override
    public boolean isOnceAliPay() {
        return mPayAlipayCheckBox.isChecked();
    }

    @Override
    public boolean isOnceUnionPay() {
        return mPayUnionpayCheckBox.isChecked();
    }

    @Override
    public void setOriginalPrice(String originalPrice) {
        mPriceOriginalTv.setText(originalPrice);
    }

    @Override
    public void setDiscountPrice(String discountPrice) {
        mPriceDiscountTv.setText(discountPrice);
    }


    @Override
    public void setDeliverPrice(String deliverPrice) {
        mPriceDeliverTv.setText(deliverPrice);
    }

    @Override
    public void setTax(String tax) {
        mPriceTraiffTv.setText(tax);
    }

    @Override
    public void setTotalCount(String count) {
        mCountAllTv.setText(count);
    }

    @Override
    public void setTotalPrice(String totalPrice) {
        mPriceAccountTv.setText(totalPrice);
    }

    @Override
    public void setRealPayPrice(String realPay) {
        mPriceRealTv.setText(realPay);
    }
}
