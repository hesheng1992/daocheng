package com.a1magway.bgg.p.order;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.CheckOrderProResult;
import com.a1magway.bgg.data.entity.OrderAddress;
import com.a1magway.bgg.data.entity.OrderDetails;
import com.a1magway.bgg.data.entity.OrderDetailsCommodity;
import com.a1magway.bgg.data.entity.OrderStatus;
import com.a1magway.bgg.data.repository.IOrderDetailsData;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.util.EntityTransUtil;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.StringFormatUtil;
import com.a1magway.bgg.v.order.IOrderDetailsV;
import com.a1magway.bgg.v.order.OrderPayActivity;
import com.almagway.common.utils.TimeUtils;

import io.reactivex.Observable;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jph on 2017/8/19.
 */
@PerActivity
public class OrderDetailsP extends BaseLoadP<OrderDetails, IOrderDetailsV> {

    @Inject
    IOrderDetailsData mOrderDetailsData;

    @Inject
    @Named(value = "OrderNum")
    String mOrderNum;

    @Inject
    ProListAdapter mAdapter;

    private OrderDetails orderDetails;

    @Inject
    public OrderDetailsP(@NonNull IOrderDetailsV view) {
        super(view);
    }

    @Nullable
    @Override
    public Observable<OrderDetails> getDataObservable() {
        //从销售订单页面进入
        if (mView.getCreatorId() != -1) {
            return mOrderDetailsData.getSaleOrderDetails(GlobalData.getInstance().getUserId(),mView.getCreatorId(),mOrderNum);
        }
        //从我的订单页面进入
        return mOrderDetailsData.getOrderDetails(GlobalData.getInstance().getUserId(), mOrderNum);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAdapter.setOnItemClickListener(new DetailsProItemCLickListener(mAdapter));

        mView.setRecyclerViewAdapter(mAdapter);

        loadData();
    }

    public void reload() {
        loadData(false);
    }

    @Override
    protected void onLoadSuccess(OrderDetails orderDetails) {
        super.onLoadSuccess(orderDetails);
        this.orderDetails = orderDetails;
        mAdapter.setList(orderDetails.getSkuList());

        OrderAddress address = orderDetails.getAddress();
        if (address != null) {
            mView.showReceiverNameTxt(address.getOrderuserName());
            mView.showReceiverPhoneTxt(address.getOrderuserPhone());
            mView.showReceiverAddressTxt(address.getOrderuserDetailAddress());
            mView.showReceiverPostcodeTxt(address.getPostCode());
        }
        int totalCount = 0;
        List<OrderDetailsCommodity> skuList = orderDetails.getSkuList();
        for (OrderDetailsCommodity commodity : skuList) {
            totalCount += commodity.getCount();
        }
        mView.showGoodsCountTxt(totalCount + "件商品");

        mView.showOrderCodeTxt(
                StringFormatUtil.format(
                        getContext(),
                        R.string.order_details_format_code,
                        orderDetails.getOrderNum()));
        mView.showTimeTxt(
                StringFormatUtil.format(
                        getContext(),
                        R.string.order_details_format_time,
                        TimeUtils.timeToStrThread(orderDetails.getOrderCreatTime())));

        if (orderDetails.getOrderStatus() < OrderStatus.WAIT_DELIVER) {
            mView.setPayWayShow(false); // 不显示支付方式

            // 显示立即支付
            mView.setPayViewShow(true);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(
                    orderDetails.getOrderCreatTime().getTime()
                            + orderDetails.getPayTime() * 60 * 1000);
            mView.showPayCountDown(calendar.getTime());
        } else {
            // 显示支付方式
            mView.setPayWayShow(true);
            String payType = EntityTransUtil.transPayTypeName(orderDetails.getPayType());
            if (!TextUtils.isEmpty(payType)) {
                mView.showPayWayTxt(
                        StringFormatUtil.format(
                                getContext(), R.string.order_details_format_pay_way, payType));
            }
            // 隐藏立即支付
            mView.setPayViewShow(false);
        }

        if (orderDetails.getOrderStatus() < OrderStatus.DELIVERED) {
            // 未发货
            mView.setDeliverShow(false);
        } else {
            mView.setDeliverShow(true);
            mView.showDeliverWayTxt(
                    StringFormatUtil.format(
                            getContext(),
                            R.string.order_details_format_deliver_way,
                            orderDetails.getExpressName()));
            mView.showDeliverNumTxt(
                    StringFormatUtil.format(
                            getContext(),
                            R.string.order_details_format_deliver_num,
                            orderDetails.getExpressNum()));
        }

        mView.showRemarkTxt(orderDetails.getRemark());
        mView.showProPriceTxt(
                StringFormatUtil.format(
                        getContext(),
                        R.string.order_details_format_pro_price,
                        StringFormatUtil.getPrice(getContext(), orderDetails.getSellPrice())));
        mView.showTaxTxt(
                StringFormatUtil.format(
                        getContext(),
                        R.string.order_details_format_tax,
                        StringFormatUtil.getPrice(getContext(), orderDetails.getTax())));
        mView.showFreightTxt(checkFreight(orderDetails));
        mView.showTotalPriceTxt(
                StringFormatUtil.format(
                        getContext(),
                        R.string.order_details_format_total_price,
                        StringFormatUtil.getPrice(getContext(), orderDetails.getTotalPrice())));
        mView.showPayPriceTxt(
                StringFormatUtil.format(
                        getContext(),
                        R.string.order_details_format_pay_price,
                        StringFormatUtil.getPrice(getContext(), orderDetails.getTotalPrice())));

        //拼团商品显示拼团须知
        if (orderDetails.getActivityType().equals("2")){
            mView.showPingtuanNotice();
        }
    }

    private CharSequence checkFreight(OrderDetails checkOrderProResult) {
        String freight =
                StringFormatUtil.format(
                        getContext(),
                        R.string.order_details_format_freight,
                        StringFormatUtil.getPrice(getContext(), checkOrderProResult.getFreight()));
        if (checkOrderProResult.getPackageMail() == CheckOrderProResult.PACKAGE_MAIL_ON) {
            String postageOff = freight + getContext().getString(R.string.cancel_postage);
            SpannableString spannableString = new SpannableString(postageOff);
            spannableString.setSpan(
                    new StrikethroughSpan(), 3, freight.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(
                    new ForegroundColorSpan(
                            getContext().getResources().getColor(R.color.text_grey)),
                    0,
                    freight.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(
                    new ForegroundColorSpan(getContext().getResources().getColor(R.color.text_red)),
                    freight.length(),
                    postageOff.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        }
        return freight;
    }

    public void onClickPay() {
        OrderPayActivity.start(getContext(), mOrderNum, -1);
    }

    @Override
    protected void onLoadFinish() {
        super.onLoadFinish();

        mView.stopRefresh();
    }

    public void doRefresh(int payType) {
        orderDetails.setOrderStatus(OrderStatus.WAIT_DELIVER);
        orderDetails.setPayType(payType);
        onLoadSuccess(orderDetails);
    }

    public String getOrderNum() {
        return mOrderNum;
    }
}
