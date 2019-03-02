package com.a1magway.bgg.util.pay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.PayType;
import com.a1magway.bgg.data.entity.WXPayInfo;
import com.a1magway.bgg.okhttp.LogUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by jph on 2017/8/22.
 */
public class WXPay {

    public static final String EXTRA_WX_PAY_RESULT_CODE = "extra_wx_pay_result_code";
    public static final String ACTION_WX_PAY_RESULT = "action_wx_pay_result";

    private Context mContext;
    private PayCallback mPayCallback;

    public WXPay(Context context) {
        mContext = context;

        mContext.registerReceiver(mWXPayResultReceiver, new IntentFilter(ACTION_WX_PAY_RESULT));
    }

    public void pay(WXPayInfo wxPayInfo, PayCallback payCallback) {
        mPayCallback = payCallback;

        final IWXAPI api = WXAPIFactory.createWXAPI(mContext, null);

        //注册APPID
        api.registerApp(wxPayInfo.getAppid());

        if (!api.isWXAppInstalled()) {
            //未安装微信
            if (mPayCallback != null) {
                mPayCallback.onFailed(mContext.getString(R.string.pay_toast_no_wechat));
            }
            return;
        }

        //调起支付
        PayReq request = new PayReq();
        request.appId = wxPayInfo.getAppid();
        request.partnerId = wxPayInfo.getPartnerid();
        request.prepayId = wxPayInfo.getPrepayid();
        request.packageValue = "Sign=WXPay";
        request.nonceStr = wxPayInfo.getNoncestr();
        request.timeStamp = wxPayInfo.getTimestamp();
        request.sign = wxPayInfo.getSign();
        api.sendReq(request);
    }

    /**
     * 根据界面生命周期销毁取消结果广播的接收
     */
    public void destroy() {
        mContext.unregisterReceiver(mWXPayResultReceiver);
    }

    private BroadcastReceiver mWXPayResultReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int resultCode = intent.getIntExtra(EXTRA_WX_PAY_RESULT_CODE, -3);

            if (mPayCallback == null) {
                return;
            }
            if (resultCode == 0) {
                //微信支付成功
                mPayCallback.onSuccess(PayType.WECHAT);
            } else {
                mPayCallback.onFailed(null);
            }
        }
    };
}
