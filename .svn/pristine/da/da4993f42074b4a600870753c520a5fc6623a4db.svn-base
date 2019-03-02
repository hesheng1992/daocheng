package com.a1magway.bgg.v.order;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.data.entity.AgainRequestCatDataTag;
import com.a1magway.bgg.data.entity.OrderDetailsCommodity;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.order.DaggerOrderCommitComponent;
import com.a1magway.bgg.di.module.order.OrderCommitModule;
import com.a1magway.bgg.p.order.OrderCommitP;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.util.code.RequestCode;
import com.a1magway.bgg.util.dialog.GeneralImageTextDialog;
import com.a1magway.bgg.util.pay.PayCallback;
import com.a1magway.bgg.v.found.GeneralWebActivity;
import com.a1magway.bgg.v.personal.AddressManagerActivity;
import com.a1magway.bgg.widget.LoadMoreRecyclerView;
import com.a1magway.bgg.widget.TitleBar;
import com.a1magway.bgg.widget.dialog.PayDialogFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 提交订单的界面
 * Created by jph on 2017/8/19.
 */
public class OrderCommitActivity extends PActivity<OrderCommitP> implements IOrderCommitV {

    public static final String EXTRA_COMMODITY_LIST = "extra_commodity_list";
    public static final String EXTRA_IS_SECKILL = "extra_is_seckill";
    public static final String EXTRA_REFRESH_TAG = "extra_refresh_tag";
    public static final String EXTRA_IS_PING_TUAN = "extra_is_ping_tuan";
    public static final String EXTRA_MY_CREATE_PT = "extra_my_create_pt";
    public static final String EXTRA_COLLAGE_ORDER_ID = "collage_order_Id";
    private static final int WEB_REULT_CODE = 101;

    @BindView(R.id.commit_title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.commit_recycler)
    LoadMoreRecyclerView mRecyclerView;
    @BindView(R.id.commit_txt_pay_price)
    TextView mPayPriceTxt;
    @BindView(R.id.commit_txt_pay)
    TextView mPayTxt;
    @BindView(R.id.pingtuan_notice)
    RelativeLayout pingtuan_notice;
    @BindView(R.id.agree_pingtuan_rule)
    CheckedTextView agree_pingtuan_rule;

    OrderCommitHeaderVO mHeaderVO;
    OrderCommitFooterVO mFooterVO;

    private int orderId = 0;
    private String orderNum = "";

    public static void start(Context context, List<OrderDetailsCommodity> commodityList, AgainRequestCatDataTag orderRefreshTag) {
        start(context, commodityList, false, orderRefreshTag);
    }

    public static void start(Context context, List<OrderDetailsCommodity> commodityList, boolean isSeckill, AgainRequestCatDataTag orderRefreshTag) {
        Intent starter = new Intent(context, OrderCommitActivity.class);
        starter.putExtra(EXTRA_COMMODITY_LIST, (Serializable) commodityList);
        starter.putExtra(EXTRA_IS_SECKILL, isSeckill);
        if (orderRefreshTag != null) {
            starter.putExtra(EXTRA_REFRESH_TAG, orderRefreshTag);
        }
        JumpUtil.startActivity(context, starter);
    }

    public static void start(Context context, List<OrderDetailsCommodity> commodityList, boolean isSeckill, AgainRequestCatDataTag orderRefreshTag,
                             boolean isPingtuan, boolean myCreatePingtuan, int collageOrderId) {
        Intent starter = new Intent(context, OrderCommitActivity.class);
        starter.putExtra(EXTRA_COMMODITY_LIST, (Serializable) commodityList);
        starter.putExtra(EXTRA_IS_SECKILL, isSeckill);
        starter.putExtra(EXTRA_IS_PING_TUAN, isPingtuan);
        starter.putExtra(EXTRA_MY_CREATE_PT, myCreatePingtuan);
        starter.putExtra(EXTRA_COLLAGE_ORDER_ID, collageOrderId);
        if (orderRefreshTag != null) {
            starter.putExtra(EXTRA_REFRESH_TAG, orderRefreshTag);
        }
        JumpUtil.startActivity(context, starter);
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);

        DaggerOrderCommitComponent.builder()
                .appComponent(appComponent)
                .orderCommitModule(new OrderCommitModule(this, getIntent()))
                .build()
                .inject(this);
    }

    @Override
    protected boolean fitsSystemWindows() {
        return true;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.order_activity_commit;
    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {//加上判断
            EventBus.getDefault().register(this);
        }
        mTitleBar.setLeftImgClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mHeaderVO = new OrderCommitHeaderVO(getContext());
        mFooterVO = new OrderCommitFooterVO(getContext());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setLoadable(false);

        mPayTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderId == 0 || orderNum.equals("")) {
                    if (mPresenter.isOverSea()) {
                        if (mHeaderVO.haiwai_checkbox.isChecked()) {
                            overSeaCommitOrder();
                        } else {
                            Toaster.showShort(getContext(), "请勾选并同意海外发货须知");
                        }
                    } else {
                        mPresenter.commitOrder(getBeizhu());
                    }
                } else {
                    OrderPayActivity.start(getContext(), orderNum, orderId);
                }
            }
        });
        mHeaderVO.mAddAddressTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressManagerActivity.startActivityForResult(getContext(), true);
            }
        });
        mHeaderVO.mSelectAddressTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressManagerActivity.startActivityForResult(getContext(), true);
            }
        });

    }

    /**
     * 海外仓订单提交需实名制
     */
    private void overSeaCommitOrder() {
        if (getIdentityNumber().trim().isEmpty() || getRealName().trim().isEmpty()) {
            GeneralImageTextDialog.show(this, R.drawable.ic_globe, R.string.dialog_add_identity_card);
            return;
        }
        mPresenter.toUserCertification(getRealName(), getIdentityNumber());
    }


    @Subscribe
    public void getEventBus(Integer num) {
        if (num == RequestCode.REQUEST_CODE_UNIONPAY_SUCCESS) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        if (EventBus.getDefault().isRegistered(this))//加上判断
            EventBus.getDefault().unregister(this);
        super.onDestroy();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == WEB_REULT_CODE) { //网页支付成功返回到打开订单详情界面
//            String orderNum=data.getStringExtra("orderNum");
//            OrderDetailsActivity.start(this,orderNum);
            finish();
            return;
        }
        mPresenter.handleSelectAddressResult(requestCode, resultCode, data);
    }

    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addHeaderView(mHeaderVO.getView());
        mRecyclerView.addFooterView(mFooterVO.getView());
    }

    @Override
    public void showDefaultAddressView(boolean show) {
        mHeaderVO.mAddAddressLayout.setVisibility(show ? View.GONE : View.VISIBLE);

        mHeaderVO.mNameTxt.setVisibility(show ? View.VISIBLE : View.GONE);
        mHeaderVO.mPhoneTxt.setVisibility(show ? View.VISIBLE : View.GONE);
        mHeaderVO.mAddressTxt.setVisibility(show ? View.VISIBLE : View.GONE);
        mHeaderVO.mPostcodeTxt.setVisibility(show ? View.VISIBLE : View.GONE);
        mHeaderVO.mSelectAddressTxt.setVisibility(show ? View.VISIBLE : View.GONE);
        mHeaderVO.mAddressInfoDividerV.setVisibility(show ? View.VISIBLE : View.GONE);
        //
        if (mPresenter.isOverSea()) {
            mHeaderVO.commitUserInfoCl.setVisibility(show ? View.VISIBLE : View.GONE);
            mHeaderVO.haiwai_checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mHeaderVO.haiwai_checkbox.isChecked()) {
                        mHeaderVO.haiwai_checkbox.setChecked(false);
                    } else {
                        mHeaderVO.haiwai_checkbox.setChecked(true);
                    }
                }
            });
            mHeaderVO.foreignDeliverNotice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "https://smjdev.1magway.com/pictures/platform/commodity/picture/html/xuzhi_fahuo.html";
                    GeneralWebActivity.start(getContext(), url, "海外发货须知");
                }
            });
        }

    }

    @Override
    public void showName(String str) {
        mHeaderVO.mNameTxt.setText(str);
    }

    @Override
    public void showPhone(String str) {
        mHeaderVO.mPhoneTxt.setText(str);
    }

    @Override
    public void showDetailsAddress(String str) {
        mHeaderVO.mAddressTxt.setText(str);
    }

    @Override
    public void showPostcode(String str) {
        mHeaderVO.mPostcodeTxt.setText(str);
    }

    @Override
    public void showTotalProPrice(String str) {
        mFooterVO.mProPriceTxt.setText(str);
    }

    @Override
    public void showTax(String str) {
        mFooterVO.mTaxTxt.setText(str);
    }

    @Override
    public void showFreight(CharSequence str) {
        mFooterVO.mFreightTxt.setText(str);
    }

    @Override
    public void showTotalCount(String str) {
        mFooterVO.mTotalCountTxt.setText(str);
    }

    @Override
    public void showTotalPrice(String str) {
        mFooterVO.mTotalPriceTxt.setText(str);
    }

    @Override
    public void showPayPrice(String str) {
        mPayPriceTxt.setText(str);
    }

    @Override
    public void backOrderInfo(String orderNum, int orderId) {
        this.orderNum = orderNum;
        this.orderId = orderId;
        OrderPayActivity.start(getContext(), orderNum, orderId);
        mPayTxt.setBackgroundColor(Color.GRAY);
        mPayTxt.setEnabled(false);
    }

    @Override
    public void showPayEnable(boolean enable) {
        mPayTxt.setEnabled(enable);
    }

    @Override
    public void showPayDialog(final String orderNum) {
        final PayDialogFragment dialogFragment = PayDialogFragment.newInstance(orderNum);
        dialogFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                //已经下单成功，隐藏支付弹窗则关闭界面
                finish();
                OrderDetailsActivity.start(getContext(), orderNum);
            }
        });
        dialogFragment.setPayCallback(new PayCallback() {
            @Override
            public void onSuccess(int type) {
                dialogFragment.dismiss();
            }

            @Override
            public void onFailed(String msg) {
                dialogFragment.dismiss();
            }
        });
        dialogFragment.show(getSupportFragmentManager(), "PayDialog");
    }

    @Override
    public void showIsOverSeaView() {
//        mHeaderVO.commitUserInfoCl.setVisibility(View.VISIBLE);
//        mHeaderVO.foreignDeliverNotice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //海外发货须知
//            }
//        });

    }

    @Override
    public String getBeizhu() {//返回备注内容
        return mFooterVO.mRemarkEdt.getText().toString();
    }

    @Override
    public String getRecerveName() {//返回收货人名字
        return mHeaderVO.mNameTxt.getText().toString();
    }

    @Override
    public String getIdentityNumber() {//返回收货人身份证号
        return mHeaderVO.commitEditInputIdentity.getText().toString();
    }

    @Override
    public void setIdentityNumber(String number) {
        if (number != null) {
            mHeaderVO.commitEditInputIdentity.setText(number);
        } else {
            mHeaderVO.commitEditInputIdentity.setText("");
        }
    }

    @Override
    public String getRealName() {
        return mHeaderVO.realName.getText().toString();
    }

    @Override
    public void setRealName(String realName) {
        if (realName != null) {
            mHeaderVO.realName.setText(realName);
        } else {
            mHeaderVO.realName.setText("");
        }
    }

    @Override
    public void showPingtuanView() {
        pingtuan_notice.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean agreePingtuanRule() {
        return agree_pingtuan_rule.isChecked();
    }

    @OnClick(R.id.pingtuan_notice)
    public void onClickPingtuanNotice(View view) {
        String url = "https://smjdev.1magway.com/pictures/platform/commodity/picture/html/xuzhi.html";
        GeneralWebActivity.start(this, url, "拼团须知");
    }

    @OnClick(R.id.agree_pingtuan_rule)
    public void onClickAgreePTRule(View v) {
        if (agree_pingtuan_rule.isChecked()) {
            agree_pingtuan_rule.setChecked(false);
        } else {
            agree_pingtuan_rule.setChecked(true);
        }
    }

}
