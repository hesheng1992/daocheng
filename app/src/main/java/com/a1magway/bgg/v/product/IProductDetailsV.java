package com.a1magway.bgg.v.product;

import com.a1magway.bgg.common.ILoadingV;
import com.a1magway.bgg.data.entity.Product;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jph on 2017/8/7.
 */
public interface IProductDetailsV extends ILoadingV {

    void showBrandTxt(String brand);

    void showNameTxt(String name);

    void showSubFragments(String brand,String title);
    //1 加入购物车 2立即购买 0表示不是加购和立即购买
    Serializable getSelectedCommodity(int flag);

    void showCountDown();

    /**
     * 是否显示秒杀倒计时
     */
    void showCountDownEnable(boolean show);

    void showBuyBottom(boolean isShowBuyBottom);

    void showUpgradeBottom(String desc, String clickDesc);

    void shareImages(List<String> urls,String QRcodeImageUrl,String shardAddHeadImageUrl);

    String getSubject_id();

    void showPingtuanView(boolean isPingtuan,Product product);
}
