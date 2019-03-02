package com.a1magway.bgg.p.order;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.ILoading;
import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.entity.OrderAliPay;
import com.a1magway.bgg.data.entity.OrderDetails;
import com.a1magway.bgg.data.entity.OrderDetailsCommodity;
import com.a1magway.bgg.data.entity.PayType;
import com.a1magway.bgg.data.entity.WXPayInfo;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IOrderDetailsData;
import com.a1magway.bgg.data.repository.IPayData;
import com.a1magway.bgg.eventbus.event.ChangeUserSettingEvent;
import com.a1magway.bgg.eventbus.event.PingtuanPaySuccess;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.util.AppManager;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.util.code.RequestCode;
import com.a1magway.bgg.util.dialog.CustomDialog;
import com.a1magway.bgg.util.pay.AliPay;
import com.a1magway.bgg.util.pay.PayCallback;
import com.a1magway.bgg.util.pay.WXPay;
import com.a1magway.bgg.v.main.MainActivity;
import com.a1magway.bgg.v.main.MainSubPages;
import com.a1magway.bgg.v.order.IOrderPayV;
import com.a1magway.bgg.v.order.OrderCommitActivity;
import com.a1magway.bgg.v.order.OrderDetailsActivity;
import com.a1magway.bgg.v.order.OrderListActivity;
import com.a1magway.bgg.v.product.ProductDetailsActivity;
import com.a1magway.bgg.v.upgrade.UpgradeGuidePager1Activity;
import com.a1magway.bgg.v.upgrade.UpgradeGuidePager2Activity;
import com.a1magway.bgg.v.upgrade.UpgradePayActivity;
import com.a1magway.bgg.v.web.WebActivity;
import com.almagway.common.AppConfig;
import com.almagway.common.log.MLog;
import com.almagway.common.utils.StringUtil;
import com.almagway.common.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.SafeObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by enid on 2018/6/23.
 */

public class OrderPayP extends BaseLoadP<OrderDetails, IOrderPayV> {

    private static final String TAG = OrderPayP.class.getSimpleName();

    private OrderDetails mOrderDetails;

    private AliPay mAliPay;
    private WXPay mWXPay;
    private ILoading mLoading;

    @Inject
    IOrderDetailsData mOrderDetailsData;

    @Inject
    IPayData mPayData;

    @Named(value = "OrderNum")
    @Inject
    String mOrderNum;

    private String activity;

    @Inject
    APIManager apiManager;

    @Inject
    public OrderPayP(@NonNull IOrderPayV view) {
        super(view);
    }

    private String oldRankName;

    private String activityType;//2为拼团商品

    @Nullable
    @Override
    public Observable<OrderDetails> getDataObservable() {
        return mOrderDetailsData.getOrderDetails(GlobalData.getInstance().getUserId(), mOrderNum);
    }


    @Override
    protected void onLoadSuccess(OrderDetails orderDetails) {
        super.onLoadSuccess(orderDetails);
        activityType = orderDetails.getActivityType();
        mOrderDetails = orderDetails;
        mView.setOriginalPrice("原价:" + String.format(getContext().getResources().getString(R.string.format_price), orderDetails.getSellPrice()));
        mView.setDiscountPrice("商品优惠:" + String.format(getContext().getResources().getString(R.string.format_price), orderDetails.getTotalDiscount()));
        mView.setDeliverPrice("运费:" + String.format(getContext().getResources().getString(R.string.format_price), orderDetails.getFreight()));
        mView.setTax("关税:" + String.format(getContext().getResources().getString(R.string.format_price), orderDetails.getTax()));
        mView.setTotalPrice("总计:" + String.format(getContext().getResources().getString(R.string.format_price), orderDetails.getTotalPrice()));
        mView.setRealPayPrice("实付款:" + String.format(getContext().getResources().getString(R.string.format_price), orderDetails.getTotalPrice()));

        int totalCount = 0;
        List<OrderDetailsCommodity> skuList = orderDetails.getSkuList();
        for (OrderDetailsCommodity commodity : skuList) {
            totalCount += commodity.getCount();
        }
        mView.setTotalCount("共计:" + totalCount + "件");

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAliPay = new AliPay(mView.getActivity());
        mWXPay = new WXPay(getContext());
        oldRankName = GlobalData.getInstance().getLoginData().getRank();

        initLoading();
        loadData();
    }

    public void clickPay(int payType, int orderId) {
        if (mOrderDetails == null) {
            ToastUtil.showShort("订单信息获取失败");
            return;
        }
        if (payType == 0) {
            if (mView.isOnceWeixinPay()) {
                executeWechat();
            } else if (mView.isOnceAliPay()) {
                executeAliPay();
            } else if (mView.isOnceUnionPay()) {
                WebActivity.startForPayResult(getContext(), AppConfig.BASE_URL + String.format(getContext().getResources().getString(R.string.pay_unionpay_web_url), orderId + "&type=Android&term=0"), mOrderDetails.getOrderNum(), RequestCode.REQUEST_CODE_UNIONPAY_SUCCESS);
//                ToastUtil.showShort("还没银联支付");
            }
        } else if (payType == 1) {
            ToastUtil.showShort("还没分期支付");
        }
    }

    /**
     * 订单支付，微信方式
     * <p>
     * 执行微信支付，一共有2个步骤：1.获取微信支付信息；2.调起微信支付
     */
    private void executeWechat() {
        showLoading();
        mPayData.getPreWXPayData(mOrderNum)
                .subscribe(new SimpleObserver<WXPayInfo>(getContext()) {
                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull WXPayInfo wxPayInfo) {
                        super.onNext(wxPayInfo);
                        invokeWXPay(wxPayInfo);
                        activity = wxPayInfo.getActivity();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        hideLoading();
                    }
                });
    }

    /**
     * 调起微信支付
     *
     * @param wxPayInfo 微信支付接口数据
     */
    public void invokeWXPay(@io.reactivex.annotations.NonNull WXPayInfo wxPayInfo) {

        mWXPay.pay(wxPayInfo, new PayCallback() {
            @Override
            public void onSuccess(int type) {
                onPaySuccess(PayType.WECHAT);
            }

            @Override
            public void onFailed(String msg) {
                if (StringUtil.isEmpty(msg)) {
                    msg = mView.getContext().getString(R.string.pay_toast_failed);
                }
                onPayFailed(msg);
            }
        });
    }

    /**
     * 订单支付，支付宝方式
     * <p>
     * 支付宝支付，共需要三个步骤，1.调用支付宝之前获取支付信息；2.调起支付宝；3.服务器验证支付结果
     */
    private void executeAliPay() {

        showLoading();
        mPayData.getPreAliPayData(mOrderNum)
                .compose(this.<OrderAliPay>bindToDestroyEvent())
                .subscribe(new SimpleObserver<OrderAliPay>(getContext()) {
                    @Override
                    public void onNext(OrderAliPay s) {
                        super.onNext(s);
                        MLog.i(TAG, "Got aliPay order info");
                        //调用支付宝之前获取支付信息后，调用支付宝
                        invokeAliPay(s.getData());
                        activity = s.getActivity();
                    }


                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        super.onError(e);
                        hideLoading();
                        onPayFailed(mView.getContext().getString(R.string.pay_toast_cancel));
                    }


                    @Override
                    public void onFinish() {
                        super.onFinish();
                        hideLoading();
                    }
                });
    }

    /**
     * 调起支付宝支付，通过订单支付接口验证
     *
     * @param aliPayString 支付宝支付串
     */
    public void invokeAliPay(String aliPayString) {
        mAliPay.pay(aliPayString)
                .flatMap(new Function<Map<String, String>, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@io.reactivex.annotations.NonNull Map<String, String> stringStringMap)
                            throws Exception {
                        MLog.i(TAG, "Alipay callback");
                        String resultStatus = stringStringMap.get("resultStatus");
                        String result = stringStringMap.get("result");
                        //得到支付宝结果并校验
                        return mPayData.checkAliPayOrderResult(resultStatus, result);
                    }
                })
                .subscribe(new SimpleObserver<String>(getContext()) {
                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull String s) {
                        onPaySuccess(PayType.ALIPAY);
                    }


                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        super.onError(e);
                        onPayFailed(mView.getContext().getString(R.string.pay_toast_cancel));
                    }
                });
    }

    public void unionPayResult() {
        onPaySuccess(PayType.UNION);
    }

    private void onPaySuccess(int payType) {
        if (activityType != null && activityType.equals("2")) {
            //如果是拼团商品，支付成功后需要调用下这个接口
            apiManager.executeCollageOrder(GlobalData.getInstance().getUserId(), mOrderNum)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(this.<APIResponse>bindToLifecycle())
                    .subscribe(new Consumer<APIResponse>() {
                        @Override
                        public void accept(APIResponse apiResponse) throws Exception {
                            showPaySuccessInfo();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            showPaySuccessInfo();
                        }
                    });
            //到订单列表页面，显示拼团成功dialog
            EventBus.getDefault().postSticky(new PingtuanPaySuccess(mOrderNum));
        } else {
            showPaySuccessInfo();
        }
    }

    private void showPaySuccessInfo() {
        if (activity != null && activity.equals("1")) {
            updateUserInfo();
        } else {
            mView.getActivity().finish();
            Toaster.showShort(getContext(), mView.getContext().getString(R.string.pay_toast_success));
            OrderListActivity.start(getContext());
        }
        AppManager.getInstance().finishActivity(OrderDetailsActivity.class);
    }

    private void onPayFailed(String msg) {
        Toaster.showShort(getContext(), msg);
    }

    private void showLoading() {
        if (mLoading != null) {
            mLoading.showLoadingDialog();
        }
    }

    private void hideLoading() {
        if (mLoading != null) {
            mLoading.hideLoadingDialog();
        }
    }

    private void initLoading() {
        if (mView.getActivity() != null && mView.getActivity() instanceof ILoading) {
            mLoading = (ILoading) mView.getActivity();
        }
    }

    private void updateUserInfo() {
        apiManager.getUserInfo(String.valueOf(GlobalData.getInstance().getUserId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<LoginData>bindToLifecycle())
                .subscribe(new Consumer<LoginData>() {
                    @Override
                    public void accept(LoginData loginData) throws Exception {
                        if (oldRankName == null || !oldRankName.equals(loginData.getRank())) {
                            showUpgradeSucceedDialog(loginData);
                        } else {
                            mView.getActivity().finish();
                            Toaster.showShort(getContext(), mView.getContext().getString(R.string.pay_toast_success));
                            OrderListActivity.start(getContext());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }


    public void showUpgradeSucceedDialog(LoginData loginData) {
        GlobalData.getInstance().setLoginData(loginData);
        EventBus.getDefault().post(new ChangeUserSettingEvent());
        String chenghao = loginData.getMemberGradeName();
        String message = "账号已成功升级为时尚买手\n获得“" + chenghao + "”称号";
        String btnText = "查看";
        CustomDialog.show(mView.getActivity(), message, btnText, R.mipmap.icon_pay_succeed, new CustomDialog.OnBtnClickListener() {
            @Override
            public void btnClick(View view) {
                CustomDialog.dismiss(mView.getActivity());
                MainActivity.start(mView.getContext(), MainSubPages.PERSONAL);//切换到个人中心
            }
        });
    }
}
