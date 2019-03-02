package com.a1magway.bgg.p.found;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.data.entity.PayType;
import com.a1magway.bgg.data.entity.WXPayInfo;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.p.BasePresenter;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.util.pay.AliPay;
import com.a1magway.bgg.util.pay.PayCallback;
import com.a1magway.bgg.util.pay.WXPay;
import com.a1magway.bgg.v.articleManager.ArticleManagerWebActivity;
import com.a1magway.bgg.v.found.IFoundV;
import com.almagway.common.AppConfig;
import com.almagway.common.utils.StringUtil;

import java.util.Map;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class FoundP extends BasePresenter<IFoundV> {




    private AliPay mAliPay;
    private WXPay mWXPay;
    private IFoundV mIFoundV;

//    @Inject
//    IPayData mPayData;

    public FoundP(IFoundV f) {
    super(f);
    mIFoundV=f;
    mAliPay=new AliPay(f.getActivity());
    mWXPay=new WXPay(f.getActivity());
}



    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {

    }



    /**
     * 订单支付，支付宝方式
     * <p>
     * 支付宝支付，共需要三个步骤，1.调用支付宝之前获取支付信息；2.调起支付宝；3.服务器验证支付结果
     */
    private void executeAliPay() {

//
//        mPayData.getPreAliPayData(mOrderNum)
//                .compose(this.<String>bindToDestroyEvent())
//                .subscribe(new SimpleObserver<String>(getContext()) {
//                    @Override
//                    public void onNext(@io.reactivex.annotations.NonNull String s) {
//                        super.onNext(s);
//                        MLog.i(TAG, "Got aliPay order info");
//                        //调用支付宝之前获取支付信息后，调用支付宝
//                        invokeAliPay(s);
//                    }
//
//
//                    @Override
//                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
//                        super.onError(e);
//
//                        onPayFailed(mView.getContext().getString(R.string.pay_toast_cancel));
//                    }
//
//
//                    @Override
//                    public void onFinish() {
//                        super.onFinish();
//                        hideLoading();
//                    }
//                });
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
                    msg = getContext().getString(R.string.pay_toast_failed);
                }
                onPayFailed(msg);
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
                        String resultStatus = stringStringMap.get("resultStatus");
                        String result = stringStringMap.get("result");
                        //得到支付宝结果并校验
//                        return mPayData.checkAliPayOrderResult(resultStatus, result);
                        return new APIManager(null).checkAliPayOrderResult(resultStatus,result);
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
                        onPayFailed(getContext().getString(R.string.pay_toast_cancel));
                    }
                });
    }

    /**
     * 去发表文章
     */
    public void createArticle(String title){
        String url = AppConfig.FOUND_WEB_URL + "/ArticleAdd?title=123&type=" + AppConfig.BASE_URL + "&user_id="
                + GlobalData.getInstance().getLoginData().getId();
        LogUtil.e("article_url", url);
        ArticleManagerWebActivity.start(getContext(), url,"文章管理");
    }



    private void onPaySuccess(int payType) {
       Toaster.showShort(getContext(), getContext().getString(R.string.pay_toast_success));
        mIFoundV.onPaySuccess(payType);
    }

    private void onPayFailed(String msg) {
        Toaster.showShort(getContext(), msg);
        mIFoundV.onPayFailed(msg);
    }

}
