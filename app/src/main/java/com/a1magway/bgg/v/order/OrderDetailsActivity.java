package com.a1magway.bgg.v.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.data.entity.OrderStatus;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.order.DaggerOrderDetailsComponent;
import com.a1magway.bgg.di.module.order.OrderDetailsModule;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.p.order.OrderDetailsP;
import com.a1magway.bgg.tool.TimeCountDownController;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.widget.LoadMoreRecyclerView;
import com.a1magway.bgg.widget.OldRefreshLayout;
import com.a1magway.bgg.widget.TitleBar;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

import java.util.Date;

/**
 * 订单详情界面
 * Created by jph on 2017/8/18.
 */
public class OrderDetailsActivity extends PActivity<OrderDetailsP> implements IOrderDetailsV {

    private static final String EXTRA_ORDER_NUM = "extra_order_num";
    private static final String EXTRA_CREATOR_ID = "extra_creator_id";
    private static final String EXTRA_INVALID = "extra_invalid";
    @BindView(R.id.details_title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.details_layout_refresh)
    OldRefreshLayout mOldRefreshLayout;
    @BindView(R.id.details_recycler)
    LoadMoreRecyclerView mRecyclerView;
    @BindView(R.id.details_layout_bottom)
    ViewGroup mBottomLayout;
    @BindView(R.id.details_txt_pay_count_down)
    TextView mPayCountDownTxt;
    @BindView(R.id.details_txt_pay)
    TextView mPayTxt;
    private boolean invalid;

    private OrderDetailsHeaderVO mHeaderVO;
    private OrderDetailsFooterVO mFooterVO;
    private TimeCountDownController timeCountDownController;

    public static void start(Context context, String orderNum) {
        Intent starter = new Intent(context, OrderDetailsActivity.class);
        starter.putExtra(EXTRA_ORDER_NUM, orderNum);
        JumpUtil.startActivity(context, starter);
    }

    public static void start(Context context, String orderNum, int creatorId, boolean invalid) {
        Intent starter = new Intent(context, OrderDetailsActivity.class);
        starter.putExtra(EXTRA_ORDER_NUM, orderNum);
        starter.putExtra(EXTRA_CREATOR_ID, creatorId);
        starter.putExtra(EXTRA_INVALID, invalid);
        JumpUtil.startActivity(context, starter);
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerOrderDetailsComponent.builder()
                .appComponent(appComponent)
                .orderDetailsModule(new OrderDetailsModule(this, getIntent().getStringExtra(EXTRA_ORDER_NUM)))
                .build()
                .inject(this);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.order_activity_details;
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

        mOldRefreshLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPresenter.reload();
            }
        });

        mHeaderVO = new OrderDetailsHeaderVO(getContext());
        mFooterVO = new OrderDetailsFooterVO(getContext());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setLoadable(false);

        //该订单是否无效,true无效，false有效
        invalid = getIntent().getBooleanExtra(EXTRA_INVALID, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timeCountDownController != null) {
            timeCountDownController.cancel();
            timeCountDownController = null;
        }
    }

    @OnClick(R.id.details_txt_pay)
    public void onClickPay(View v) {
        mPresenter.onClickPay();
//        final PayDialogFragment dialogFragment = PayDialogFragment.newInstance(mPresenter.getOrderNum());
//        dialogFragment.setPayCallback(new PayCallback() {
//
//            @Override
//            public void onSuccess(int payType) {
//                dialogFragment.dismiss();
//                mPresenter.doRefresh(payType);
//            }
//
//            @Override
//            public void onFailed(String msg) {
//                dialogFragment.dismiss();
//            }
//        });
//        dialogFragment.show(getSupportFragmentManager(), "PayDialog");
    }

    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addHeaderView(mHeaderVO.getView());
        mRecyclerView.addFooterView(mFooterVO.getView());
    }

    @Override
    public void stopRefresh() {
        if (mOldRefreshLayout != null) {
            mOldRefreshLayout.refreshComplete();
        }
    }

    @Override
    public void showReceiverNameTxt(String str) {
        mHeaderVO.mNameTxt.setText(str);
    }

    @Override
    public void showReceiverPhoneTxt(String str) {
        mHeaderVO.mPhoneTxt.setText(str);
    }

    @Override
    public void showReceiverAddressTxt(String str) {
        mHeaderVO.mAddressTxt.setText(str);
    }

    @Override
    public void showReceiverPostcodeTxt(String str) {
        mHeaderVO.mPostcodeTxt.setText(str);
    }

    @Override
    public void showOrderCodeTxt(String str) {
        mHeaderVO.mCodeTxt.setText(str);
    }

    @Override
    public void showTimeTxt(String str) {
        mHeaderVO.mTimeTxt.setText(str);
    }

    @Override
    public void setPayWayShow(boolean show) {
        if (invalid) {
            show = false;
        }
        mFooterVO.mPayWayTxt.setVisibility(show ? View.VISIBLE : View.GONE);
        mFooterVO.mPayWayDividerV.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showPayWayTxt(String str) {
        mFooterVO.mPayWayTxt.setText(str);
    }

    @Override
    public void setDeliverShow(boolean show) {
        mFooterVO.mDeliverWayTxt.setVisibility(show ? View.VISIBLE : View.GONE);
        mFooterVO.mDeliverNumTxt.setVisibility(show ? View.VISIBLE : View.GONE);
        mFooterVO.mDeliverDividerV.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showDeliverWayTxt(String str) {
        if (str.equals("物流信息：null")) {
            mFooterVO.mDeliverWayTxt.setVisibility(View.GONE);
        } else {
            mFooterVO.mDeliverWayTxt.setText(str);
        }
    }

    @Override
    public void showDeliverNumTxt(String str) {
        if (str.equals("单号：null")) {
            mFooterVO.mDeliverNumTxt.setVisibility(View.GONE);
            if (mFooterVO.mDeliverWayTxt.getVisibility() == View.GONE) {
                mFooterVO.mDeliverDividerV.setVisibility(View.GONE);
            }
        } else {
            mFooterVO.mDeliverNumTxt.setText(str);
        }
    }

    @Override
    public void showRemarkTxt(String str) {
        mFooterVO.mRemarkTxt.setText(str);
    }

    @Override
    public void showProPriceTxt(String str) {
        mFooterVO.mProPriceTxt.setText(str);
        if (invalid) {
            mFooterVO.mProPriceTxt.setVisibility(View.GONE);
        }
    }

    @Override
    public void showTaxTxt(String str) {
        mFooterVO.mTaxTxt.setText(str);
        if (invalid) {
            mFooterVO.mTaxTxt.setVisibility(View.GONE);
        }
    }

    @Override
    public void showFreightTxt(CharSequence str) {
        mFooterVO.mFreightTxt.setText(str);
        if (invalid) {
            mFooterVO.mFreightTxt.setVisibility(View.GONE);
        }
    }

    @Override
    public void showTotalPriceTxt(String str) {
        mFooterVO.mTotalPriceTxt.setText(str);
        if (invalid) {
            mFooterVO.mTotalPriceTxt.setVisibility(View.GONE);
        }
    }

    @Override
    public void showPayPriceTxt(String str) {
        mFooterVO.mPayPriceTxt.setText(str);
        if (invalid) {
            mFooterVO.mPayPriceTxt.setVisibility(View.GONE);
        }
    }

    @Override
    public void showGoodsCountTxt(String str) {
        mFooterVO.mGoodsCount.setText(str);
        if (invalid) {
            mFooterVO.mPayPriceTxt.setVisibility(View.GONE);
        }
    }

    @Override
    public void setPayViewShow(boolean show) {
        if (getIntent().getIntExtra(EXTRA_CREATOR_ID, -1) != -1) {
            show = false;
        }
        mBottomLayout.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showPayCountDown(Date invalidDate) {
        timeCountDownController = new TimeCountDownController(invalidDate, mPayCountDownTxt,
                getString(R.string.order_details_format_pay_count_down),
                new TimeCountDownController.CountDownListener() {
                    @Override
                    public void onFinish() {
                        setPayViewShow(false);
//                        mPresenter.reload();
                    }
                }).start();
    }


    @Override
    public int getCreatorId() {
        return getIntent().getIntExtra(EXTRA_CREATOR_ID, -1);
    }

    @Override
    public void showPingtuanNotice() {

    }
}
