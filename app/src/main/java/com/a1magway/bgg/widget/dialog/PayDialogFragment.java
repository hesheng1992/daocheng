package com.a1magway.bgg.widget.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioGroup;

import com.a1magway.bgg.App;
import com.a1magway.bgg.R;
import com.a1magway.bgg.common.ILoading;
import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.data.entity.OrderAliPay;
import com.a1magway.bgg.data.entity.PayType;
import com.a1magway.bgg.data.entity.WXPayInfo;
import com.a1magway.bgg.data.repository.IPayData;
import com.almagway.common.utils.StringUtil;
import com.a1magway.bgg.di.component.pay.DaggerPayComponent;
import com.a1magway.bgg.di.module.pay.PayModule;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.util.pay.AliPay;
import com.a1magway.bgg.util.pay.PayCallback;
import com.a1magway.bgg.util.pay.PaySelectCallback;
import com.a1magway.bgg.util.pay.WXPay;
import com.almagway.common.log.MLog;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 选择支付方式的弹窗
 * Created by jph on 2017/8/21.
 */
public class PayDialogFragment extends BottomDialogFragment {

    private static final String TAG = PayDialogFragment.class.getSimpleName();

    private static final String EXTRA_ORDER_NUM = "extra_order_num";
    public static final String ALI_TAG = "ali";
    public static final String WECHAT_TAG = "wechat";

    private AliPay mAliPay;
    private WXPay mWXPay;
    private ILoading mLoading;

    private PayCallback mPayCallback;
    private PaySelectCallback selectCallback;

    @Inject
    IPayData mPayData;
    @Named(value = "OrderNum")
    @Inject
    String mOrderNum;

    @BindView(R.id.pay_rgroup_way)
    RadioGroup mRgroup;


    public static PayDialogFragment newInstance(String orderNum) {

        Bundle args = new Bundle();
        args.putString(EXTRA_ORDER_NUM, orderNum);

        PayDialogFragment fragment = new PayDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getContentViewLayoutId() {
        return R.layout.dialog_select_pay;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DaggerPayComponent.builder()
            .appComponent(((App) getActivity().getApplication()).getAppComponent())
            .payModule(new PayModule(getArguments().getString(EXTRA_ORDER_NUM)))
            .build()
            .inject(this);

        mAliPay = new AliPay(getActivity());
        mWXPay = new WXPay(getContext());

        initLoading();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        mWXPay.destroy();
    }


    @OnClick(R.id.pay_txt_confirm)
    public void onClick(View view) {
        if (mRgroup.getCheckedRadioButtonId() == R.id.pay_rbtn_alipay) {
            if (selectCallback == null) {
                executeAliPay();
            } else {
                selectCallback.select(ALI_TAG);
            }
        } else {
            if (selectCallback == null) {
                executeWechat();
            } else {
                selectCallback.select(WECHAT_TAG);
            }
        }
    }


    public void setPayCallback(PayCallback payCallback) {
        mPayCallback = payCallback;
    }


    public void setSelectCallback(PaySelectCallback selectCallback) {
        this.selectCallback = selectCallback;
    }


    /**
     * 订单支付，支付宝方式
     * <p>
     * 支付宝支付，共需要三个步骤，1.调用支付宝之前获取支付信息；2.调起支付宝；3.服务器验证支付结果
     */
    private void executeAliPay() {

        showLoading();
        mPayData.getPreAliPayData(mOrderNum)
            .compose(this.<OrderAliPay>bindToLifecycle())
            .subscribe(new SimpleObserver<OrderAliPay>(getContext()) {
                @Override
                public void onNext(@NonNull OrderAliPay s) {
                    super.onNext(s);
                    MLog.i(TAG, "Got aliPay order info");
                    //调用支付宝之前获取支付信息后，调用支付宝
                    invokeAliPay(s.getData());
                }


                @Override
                public void onError(@NonNull Throwable e) {
                    super.onError(e);
                    Toaster.showShort(getContext(), getString(R.string.pay_toast_cancel));
                    hideLoading();
                    if (mPayCallback != null) {
                        mPayCallback.onFailed(null);
                    }
                }


                @Override
                public void onFinish() {
                    super.onFinish();
                    hideLoading();
                }
            });
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
                public void onNext(@NonNull WXPayInfo wxPayInfo) {
                    super.onNext(wxPayInfo);
                    invokeWXPay(wxPayInfo);
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
    public void invokeWXPay(@NonNull WXPayInfo wxPayInfo) {

        mWXPay.pay(wxPayInfo, new PayCallback() {
            @Override
            public void onSuccess(int type) {
                if (mPayCallback != null) {
                    Toaster.showShort(getContext(), getString(R.string.pay_toast_success));
                    mPayCallback.onSuccess(PayType.WECHAT);
                }
            }


            @Override
            public void onFailed(String msg) {
                if (StringUtil.isEmpty(msg)) {
                    msg = getString(R.string.pay_toast_failed);
                }
                Toaster.showShort(getContext(), msg);
                if (mPayCallback != null) {
                    mPayCallback.onFailed(msg);
                }
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
                public ObservableSource<String> apply(@NonNull Map<String, String> stringStringMap)
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
                public void onNext(@NonNull String s) {
                    if (mPayCallback != null) {
                        Toaster.showShort(getContext(), getString(R.string.pay_toast_success));
                        mPayCallback.onSuccess(PayType.ALIPAY);
                    }
                }


                @Override
                public void onError(@NonNull Throwable e) {
                    super.onError(e);
                    Toaster.showShort(getContext(), getString(R.string.pay_toast_cancel));
                    if (mPayCallback != null) {
                        mPayCallback.onFailed(null);
                    }
                }
            });
    }


    /**
     * 调起支付宝支付，通过会员支付接口验证
     *
     * @param aliPayString 支付宝支付串
     */
    public void invokeAliPayMember(String aliPayString) {
        mAliPay.pay(aliPayString)
            .flatMap(new Function<Map<String, String>, ObservableSource<String>>() {
                @Override
                public ObservableSource<String> apply(@NonNull Map<String, String> stringStringMap)
                    throws Exception {
                    MLog.i(TAG, "Alipay callback");
                    String resultStatus = stringStringMap.get("resultStatus");
                    String result = stringStringMap.get("result");
                    //得到支付宝结果并校验
                    return mPayData.checkAliPayMemberResult(resultStatus, result);
                }
            })
            .subscribe(new SimpleObserver<String>(getContext()) {
                @Override
                public void onNext(@NonNull String s) {
                    if (mPayCallback != null) {
                        Toaster.showShort(getContext(), getString(R.string.pay_toast_success));
                        mPayCallback.onSuccess(PayType.ALIPAY);
                    }
                }


                @Override
                public void onError(@NonNull Throwable e) {
                    super.onError(e);
                    Toaster.showShort(getContext(), getString(R.string.pay_toast_cancel));
                    if (mPayCallback != null) {
                        mPayCallback.onFailed(null);
                    }
                }
            });
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
        if (getActivity() != null && getActivity() instanceof ILoading) {
            mLoading = (ILoading) getActivity();
        }
    }
}
