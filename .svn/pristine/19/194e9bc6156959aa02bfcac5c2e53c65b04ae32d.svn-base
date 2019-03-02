package com.a1magway.bgg.v.order;

import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.common.ILoadingV;

/**
 * Created by jph on 2017/8/19.
 */
public interface IOrderCommitV extends ILoadingV {

    void finish();

    void setRecyclerViewAdapter(RecyclerView.Adapter adapter);

    /**
     * 是否显示默认地址信息
     *
     * @param show
     */
    void showDefaultAddressView(boolean show);

    void showName(String str);

    void showPhone(String str);

    void showDetailsAddress(String str);

    void showPostcode(String str);

    void showTotalProPrice(String str);

    void showTax(String str);

    void showFreight(CharSequence str);

    void showTotalCount(String str);

    void showTotalPrice(String str);

    void showPayPrice(String str);

    void backOrderInfo(String orderNum, int orderId);

    /**
     * 显示支付按钮是否可点击
     *
     * @param enable
     */
    void showPayEnable(boolean enable);

    void showPayDialog(String orderNum);

    /**
     * 显示海外仓布局
     */
    void showIsOverSeaView();

    /**
     * 得到备注内容
     */
    String getBeizhu();

    /**
     * 得到收件人尼呢
     */
    String getRecerveName();

    /**
     * 得到收件人身份号码
     */
    String getIdentityNumber();

    void setIdentityNumber(String number);

    /**
     * 得到收件人真实姓名
     */
    String getRealName();

    void setRealName(String realName);

    void showPingtuanView();

    boolean agreePingtuanRule();
}
