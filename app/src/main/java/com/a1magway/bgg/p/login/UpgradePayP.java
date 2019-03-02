package com.a1magway.bgg.p.login;

import android.support.annotation.NonNull;

import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.entity.UpgradePayData;
import com.a1magway.bgg.data.entity.WXPayInfo;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.refactor.BasePresenter;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.util.pay.AliPay;
import com.a1magway.bgg.util.pay.PayCallback;
import com.a1magway.bgg.util.pay.WXPay;
import com.a1magway.bgg.v.main.MainActivity;
import com.a1magway.bgg.v.main.MainSubPages;
import com.a1magway.bgg.v.upgrade.UpgradePayActivity;
import com.a1magway.bgg.v.upgrade.UpgradePayContract;
import com.a1magway.bgg.widget.dialog.MProgressDialog;
import com.almagway.common.log.MLog;
import com.almagway.common.utils.ToastUtil;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * 升级支付
 * Created by enid on 2018/8/4.
 */

public class UpgradePayP extends BasePresenter<UpgradePayContract.View> implements UpgradePayContract.Presenter {
    private String type;//购买的服务类型（1-升级到B，2-升级到BD）
    @Inject
    APIManager apiManager;

    private AliPay mAliPay;
    private WXPay mWXPay;

    private String oldRankName;

    @Inject
    public UpgradePayP(@NonNull UpgradePayContract.View view, @Named(value = "payMoney") int pay_money) {
        super(view);
        if (pay_money == 999) {
            type = "1";
        } else {
            type = "2";
        }
        LogUtil.e("type", pay_money + " " + type);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAliPay = new AliPay(mView.getActivity());
        mWXPay = new WXPay(getContext());
        oldRankName = GlobalData.getInstance().getLoginData().getRank();
    }

    @Override
    public void toWeixinPay() {
        showLoading();
        apiManager.weixinPayUpgrade(String.valueOf(GlobalData.getInstance().getUserId()), type)
                .subscribeOn(Schedulers.io())
                .compose(this.<WXPayInfo>bindToDestroyEvent())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WXPayInfo>() {
                    @Override
                    public void accept(WXPayInfo wxPayInfo) throws Exception {
                        hideLoading();
                        invokeWXPay(wxPayInfo);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        hideLoading();
                        Toaster.showShort(getContext(), "获取支付信息失败");
                    }
                });

    }

    @Override
    public void toAliPay() {
        showLoading();
        apiManager.aliPayUpgrade(String.valueOf(GlobalData.getInstance().getUserId()), type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<UpgradePayData>bindToDestroyEvent())
                .subscribe(new Consumer<UpgradePayData>() {
                    @Override
                    public void accept(UpgradePayData upgradePayData) throws Exception {
                        hideLoading();
                        invokeAliPay(upgradePayData.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        hideLoading();
                        onPayFailed();
                    }
                });
    }

    /**
     * 调起微信支付
     */
    public void invokeWXPay(@io.reactivex.annotations.NonNull WXPayInfo wxPayInfo) {

        mWXPay.pay(wxPayInfo, new PayCallback() {
            @Override
            public void onSuccess(int type) {
                onPaySuccess();
            }

            @Override
            public void onFailed(String msg) {
                onPayFailed();
            }
        });
    }


    /**
     * 调起支付宝支付，通过订单支付接口验证
     */
    public void invokeAliPay(String aliPayString) {
        mAliPay.pay(aliPayString)
                .flatMap(new Function<Map<String, String>, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@io.reactivex.annotations.NonNull Map<String, String> stringStringMap)
                            throws Exception {
                        MLog.i("aliPay", "Alipay callback");
                        String resultStatus = stringStringMap.get("resultStatus");
                        String result = stringStringMap.get("result");
                        //得到支付宝结果并校验
                        return apiManager.checkAliPayOrderResult(resultStatus, result);
                    }
                })
                .subscribe(new SimpleObserver<String>(getContext()) {
                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull String s) {
                        onPaySuccess();
                    }


                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        super.onError(e);
                        onPayFailed();
                    }
                });
    }

    //付钱成功更新用户信息
    private void updateUserInfo() {
        apiManager.getUserInfo(String.valueOf(GlobalData.getInstance().getUserId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginData>() {
                    @Override
                    public void accept(LoginData loginData) throws Exception {
                        GlobalData.getInstance().setLoginData(loginData);
                        if (oldRankName == null || !oldRankName.equals(loginData.getRank())) {
                            mView.showUpgradeSucceedDialog();
                        }else {
                            MainActivity.start(getContext(), MainSubPages.PERSONAL);
                        }
                    }
                });
    }

    private void onPaySuccess() {
        updateUserInfo();
        Toaster.showShort(getContext(), "支付成功");
    }

    private void onPayFailed() {
        Toaster.showShort(getContext(), "取消付款");
    }


    private void showLoading() {
        MProgressDialog.showProgress(getContext());

    }

    private void hideLoading() {
        MProgressDialog.dismissProgress();
    }


}
