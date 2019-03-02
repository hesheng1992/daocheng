package com.a1magway.bgg.v.product;

import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.data.entity.Commodity;
import com.a1magway.bgg.v.IView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by jph on 2017/8/8.
 */
public interface IProDetailsInfoV extends IView {

    void showCoverBanner(List<String> coverList);

    void showStoreInfoTxt(String str);

    void showPriceTxt(String str, boolean isShow);

    void showOriginPriceTxt(String str);

    void showDiscountInfoTxt(String str);

    /**
     * 是否显示原价跟折扣信息
     *
     * @param visible
     */
    void showDiscountTxtVisible(boolean visible);

    void showStockTxt(String str);

    void showCommDialogCallBackData(Commodity mSelectedCommodityDialog);

    /**
     * 显示规格数据
     *
     * @param allSkuObservable
     */
    void showSkuContent(Observable<Map<String, String>> allSkuObservable);

    /**
     * 设置选中的规格组合
     *
     * @param selectedSku
     */
    void setSelectedSku(Map<String, String> selectedSku);

    /**
     * 显示仓库信息弹窗
     *
     * @param message
     */
    void showStoreInfoDialog(String message);

    /**
     * 是否选中完整的规格组合
     *
     * @return
     */
    boolean isSelectedWholeSku();

    boolean isSelectedWholeSkuDialog();

    /**
     * 显示是否收藏
     */
    void showIsCollected(boolean isCollected);

    /**
     *显示商品简介
     * @param info
     */
    void showProductInfo(String info);


    void setPingtuanRecyclerView(RecyclerView.Adapter adapter);

    void showPingtuanMoreView(int count);

    void setPingtuanDownTime(long endTime);

    void noPeoplePingtuan();//显示没有人拼团

    void showPingtuanCurrentCount(int count);//显示当前多少人发起拼团
}
