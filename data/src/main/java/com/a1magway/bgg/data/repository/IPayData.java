package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.OrderAliPay;
import com.a1magway.bgg.data.entity.WXPayInfo;

import io.reactivex.Observable;

/**
 * Created by jph on 2017/8/22.
 */
public interface IPayData {

    /**
     * 得到需要提交给支付宝的信息
     *
     * @param orderNum
     * @return
     */
    Observable<OrderAliPay> getPreAliPayData(String orderNum);

    /**
     * 检查支付宝支付结果
     *
     * @param resultStatus
     * @param result
     * @return
     */
    Observable<String> checkAliPayOrderResult(String resultStatus, String result);

    /**
     * 检查支付宝支付结果
     *
     * @param resultStatus
     * @param result
     * @return
     */
    Observable<String> checkAliPayMemberResult(String resultStatus, String result);


    /**
     * 得到需要提交给微信支付的信息
     *
     * @return
     */
    Observable<WXPayInfo> getPreWXPayData(String orderNum);
}
