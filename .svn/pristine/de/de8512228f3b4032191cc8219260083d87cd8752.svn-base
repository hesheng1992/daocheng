package com.a1magway.bgg.v.order;

import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.common.ILoadingV;

import java.util.Date;

/**
 * Created by jph on 2017/8/19.
 */
public interface IOrderDetailsV extends ILoadingV {

    void setRecyclerViewAdapter(RecyclerView.Adapter adapter);

    void stopRefresh();

    void showReceiverNameTxt(String str);

    void showReceiverPhoneTxt(String str);

    void showReceiverAddressTxt(String str);

    void showReceiverPostcodeTxt(String str);

    void showOrderCodeTxt(String str);

    void showTimeTxt(String str);


    /**
     * 是否显示支付方式
     *
     * @param show
     */
    void setPayWayShow(boolean show);

    void showPayWayTxt(String str);

    /**
     * 是否显示物流相关
     *
     * @param show
     */
    void setDeliverShow(boolean show);

    void showDeliverWayTxt(String str);

    void showDeliverNumTxt(String str);

    void showRemarkTxt(String str);

    void showProPriceTxt(String str);

    void showTaxTxt(String str);

    void showFreightTxt(CharSequence str);

    void showTotalPriceTxt(String str);

    void showPayPriceTxt(String str);

    void showGoodsCountTxt(String str);

    /**
     * 是否显示立即支付区域
     *
     * @param show
     */
    void setPayViewShow(boolean show);

    /**
     * 显示支付倒计时
     *
     * @param invalidDate 截止时间
     */
    void showPayCountDown(Date invalidDate);

    /**
     * 销售订单详情接口，需要的参数
     * @return CreatorId
     */
    int getCreatorId();

    void showPingtuanNotice();//显示拼团须知

}
